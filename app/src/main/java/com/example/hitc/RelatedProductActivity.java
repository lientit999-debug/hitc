package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RelatedProductActivity extends AppCompatActivity {

    TextView tvRelatedTitle;
    ListView listViewRelated;
    ArrayList<Product> relatedProducts;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_related_product);

        tvRelatedTitle = findViewById(R.id.tvRelatedTitle);
        listViewRelated = findViewById(R.id.listViewRelated);

        String category = getIntent().getStringExtra("category");
        if (category == null) category = "Sản phẩm";

        tvRelatedTitle.setText("Sản phẩm liên quan - " + category);

        relatedProducts = new ArrayList<>();

        if (category.equals("Áo")) {
            relatedProducts.add(new Product(10, "Áo khoác", "Áo khoác đẹp", 450000, "Áo"));
            relatedProducts.add(new Product(11, "Áo polo", "Áo polo trẻ trung", 220000, "Áo"));
        } else if (category.equals("Quần")) {
            relatedProducts.add(new Product(12, "Quần short", "Quần short mát mẻ", 180000, "Quần"));
            relatedProducts.add(new Product(13, "Quần kaki", "Quần kaki lịch sự", 320000, "Quần"));
        } else if (category.equals("Giày")) {
            relatedProducts.add(new Product(14, "Giày chạy bộ", "Đi rất êm", 550000, "Giày"));
            relatedProducts.add(new Product(15, "Giày da", "Phong cách lịch lãm", 700000, "Giày"));
        } else {
            relatedProducts.add(new Product(16, "Ví nam", "Ví da tiện lợi", 200000, "Phụ kiện"));
            relatedProducts.add(new Product(17, "Nón", "Nón thời trang", 120000, "Phụ kiện"));
        }

        adapter = new ProductAdapter(this, relatedProducts);
        listViewRelated.setAdapter(adapter);

        listViewRelated.setOnItemClickListener((parent, view, position, id) -> {
            Product product = relatedProducts.get(position);
            Intent intent = new Intent(RelatedProductActivity.this, DetailActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
    }
}