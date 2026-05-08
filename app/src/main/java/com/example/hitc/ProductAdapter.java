package com.example.hitc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);

        Product product = productList.get(position);

        tvProductName.setText(product.getName());
        
        // Định dạng tiền tệ VNĐ
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        tvProductPrice.setText(format.format(product.getPrice()) + " đ");

        // Xử lý ảnh thông minh: Ưu tiên URL -> Sau đó đến Resource ID
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(24));
        
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(product.getImageUrl())
                    .apply(options)
                    .placeholder(R.drawable.laptop) // Ảnh chờ khi đang tải
                    .error(R.drawable.laptop)       // Ảnh hiện khi lỗi URL
                    .into(imgProduct);
        } else {
            int resId = product.getImageResource() != 0 ? product.getImageResource() : R.drawable.laptop;
            Glide.with(context)
                    .load(resId)
                    .apply(options)
                    .into(imgProduct);
        }

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
        });

        return convertView;
    }
}