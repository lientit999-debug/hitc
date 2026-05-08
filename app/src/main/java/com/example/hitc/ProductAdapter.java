package com.example.hitc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        // 1. Ánh xạ view
        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
        Button btnDetail = convertView.findViewById(R.id.btnDetail);

        final Product product = productList.get(position);

        // 2. Đổ dữ liệu
        tvProductName.setText(product.getName());
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        tvProductPrice.setText(format.format(product.getPrice()) + " đ");

        // 3. Xử lý sự kiện click mở trang Chi tiết
        View.OnClickListener openDetail = v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product", product);
            // Nếu context không phải là Activity (hiếm gặp), thêm flag này để tránh crash
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        };

        // Gán sự kiện click cho cả ô sản phẩm và nút bấm
        convertView.setOnClickListener(openDetail);
        if (btnDetail != null) {
            btnDetail.setOnClickListener(openDetail);
        }

        // 4. Tải ảnh bằng Glide
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(24));
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(product.getImageUrl())
                    .apply(options)
                    .placeholder(R.drawable.laptop)
                    .error(R.drawable.laptop)
                    .into(imgProduct);
        } else {
            int resId = (product.getImageResource() != 0) ? product.getImageResource() : R.drawable.laptop;
            Glide.with(context)
                    .load(resId)
                    .apply(options)
                    .into(imgProduct);
        }

        return convertView;
    }
}