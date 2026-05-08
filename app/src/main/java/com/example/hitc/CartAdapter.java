package com.example.hitc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private final Activity context;
    private final List<CartItem> cartList;
    private final Runnable onCartChanged;

    public CartAdapter(Activity context, List<CartItem> cartList, Runnable onCartChanged) {
        super(context, R.layout.item_cart, cartList);
        this.context = context;
        this.cartList = cartList;
        this.onCartChanged = onCartChanged;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        TextView tvCartName = convertView.findViewById(R.id.tvCartName);
        TextView tvCartPrice = convertView.findViewById(R.id.tvCartPrice);
        TextView tvCartQuantity = convertView.findViewById(R.id.tvCartQuantity);
        ImageView imgCart = convertView.findViewById(R.id.imgCart);
        AppCompatButton btnMinus = convertView.findViewById(R.id.btnMinus);
        AppCompatButton btnPlus = convertView.findViewById(R.id.btnPlus);
        ImageButton btnRemove = convertView.findViewById(R.id.btnRemove);

        CartItem item = cartList.get(position);
        Product product = item.getProduct();

        if (product != null) {
            tvCartName.setText(product.getName());

            // Định dạng tiền tệ Việt Nam
            NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
            tvCartPrice.setText(format.format(item.getTotalPrice()) + " đ");
            tvCartQuantity.setText(String.valueOf(item.getQuantity()));

            // Tải ảnh an toàn với Glide (Bo góc 16px)
            RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(16));
            if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
                Glide.with(context)
                        .load(product.getImageUrl())
                        .apply(requestOptions)
                        .placeholder(R.drawable.laptop)
                        .error(R.drawable.laptop)
                        .into(imgCart);
            } else {
                int resId = product.getImageResource() != 0 ? product.getImageResource() : R.drawable.laptop;
                Glide.with(context)
                        .load(resId)
                        .apply(requestOptions)
                        .into(imgCart);
            }

            // Sự kiện tăng/giảm số lượng
            btnPlus.setOnClickListener(v -> {
                CartManager.increase(product);
                notifyDataSetChanged();
                onCartChanged.run();
            });

            btnMinus.setOnClickListener(v -> {
                CartManager.decrease(product);
                notifyDataSetChanged();
                onCartChanged.run();
            });

            btnRemove.setOnClickListener(v -> {
                CartManager.remove(product);
                notifyDataSetChanged();
                onCartChanged.run();
            });
        }

        return convertView;
    }
}