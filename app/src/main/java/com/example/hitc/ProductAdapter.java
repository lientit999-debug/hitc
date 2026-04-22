package com.example.hitc;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private final Activity context;
    private final List<Product> productList;

    public ProductAdapter(Activity context, List<Product> productList) {
        super(context, R.layout.item_product, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_product, parent, false);
        }

        TextView tvProductName = rowView.findViewById(R.id.tvProductName);
        TextView tvProductDesc = rowView.findViewById(R.id.tvProductDesc);
        TextView tvProductPrice = rowView.findViewById(R.id.tvProductPrice);
        Button btnDetail = rowView.findViewById(R.id.btnDetail);

        Product product = productList.get(position);

        tvProductName.setText(product.getName());
        tvProductDesc.setText(product.getDescription());
        tvProductPrice.setText("Giá: " + product.getPrice() + " đ");

        btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
        });

        return rowView;
    }
}