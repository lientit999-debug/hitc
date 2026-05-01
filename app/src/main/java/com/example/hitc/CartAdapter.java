package com.example.hitc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_cart, parent, false);
        }

        TextView tvCartName = rowView.findViewById(R.id.tvCartName);
        TextView tvCartPrice = rowView.findViewById(R.id.tvCartPrice);
        TextView tvCartQuantity = rowView.findViewById(R.id.tvCartQuantity);
        ImageView imgCart = rowView.findViewById(R.id.imgCart);
        AppCompatButton btnMinus = rowView.findViewById(R.id.btnMinus);
        AppCompatButton btnPlus = rowView.findViewById(R.id.btnPlus);
        ImageButton btnRemove = rowView.findViewById(R.id.btnRemove);

        CartItem item = cartList.get(position);

        tvCartName.setText(item.getProduct().getName());
        imgCart.setImageResource(item.getProduct().getImageResource());
        
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        tvCartPrice.setText(format.format(item.getTotalPrice()) + " đ");
        tvCartQuantity.setText(String.valueOf(item.getQuantity()));

        btnPlus.setOnClickListener(v -> {
            CartManager.increase(item.getProduct());
            notifyDataSetChanged();
            onCartChanged.run();
        });

        btnMinus.setOnClickListener(v -> {
            CartManager.decrease(item.getProduct());
            notifyDataSetChanged();
            onCartChanged.run();
        });

        btnRemove.setOnClickListener(v -> {
            CartManager.remove(item.getProduct());
            notifyDataSetChanged();
            onCartChanged.run();
        });

        return rowView;
    }
}