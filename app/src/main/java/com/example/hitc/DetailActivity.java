package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailName, tvDetailDescription, tvDetailPrice, tvDetailCategory;
    Button btnAddToCart, btnGoCart, btnRelated;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailCategory = findViewById(R.id.tvDetailCategory);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnGoCart = findViewById(R.id.btnGoCart);
        btnRelated = findViewById(R.id.btnRelated);

        product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            tvDetailName.setText(product.getName());
            tvDetailDescription.setText(product.getDescription());
            tvDetailPrice.setText("Giá: " + product.getPrice() + " đ");
            tvDetailCategory.setText("Danh mục: " + product.getCategory());
        }

        btnAddToCart.setOnClickListener(v -> {
            CartManager.addToCart(product);
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });

        btnGoCart.setOnClickListener(v ->
                startActivity(new Intent(DetailActivity.this, CartActivity.class)));

        btnRelated.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, RelatedProductActivity.class);
            intent.putExtra("category", product.getCategory());
            startActivity(intent);
        });
    }
}