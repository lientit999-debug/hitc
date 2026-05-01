package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailName, tvDetailDescription, tvDetailPrice, tvDetailCategory;
    ImageView imgDetail;
    Button btnAddToCart, btnRelated;
    View btnGoCart;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailCategory = findViewById(R.id.tvDetailCategory);
        imgDetail = findViewById(R.id.imgDetail);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnGoCart = findViewById(R.id.btnGoCart);
        btnRelated = findViewById(R.id.btnRelated);

        product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            tvDetailName.setText(product.getName());
            tvDetailDescription.setText(product.getDescription());
            imgDetail.setImageResource(product.getImageResource());
            
            NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
            tvDetailPrice.setText(format.format(product.getPrice()) + " đ");
            
            tvDetailCategory.setText(getString(R.string.detail_category_prefix) + product.getCategory());
        }

        btnAddToCart.setOnClickListener(v -> {
            CartManager.addToCart(product);
            Toast.makeText(this, getString(R.string.detail_add_to_cart_success, product.getName()), Toast.LENGTH_SHORT).show();
        });

        btnGoCart.setOnClickListener(v ->
                startActivity(new Intent(DetailActivity.this, CartActivity.class)));

        btnRelated.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, RelatedProductActivity.class);
            intent.putExtra("category", product.getCategory());
            startActivity(intent);
        });
    }

    public void onBackPressed(View view) {
        finish();
    }
}