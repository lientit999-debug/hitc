package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

        // Ánh xạ View
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailCategory = findViewById(R.id.tvDetailCategory);
        imgDetail = findViewById(R.id.imgDetail);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnGoCart = findViewById(R.id.btnGoCart);
        btnRelated = findViewById(R.id.btnRelated);

        // Nhận dữ liệu an toàn
        try {
            product = (Product) getIntent().getSerializableExtra("product");
        } catch (Exception e) {
            Log.e("DETAIL_ERROR", "Lỗi nhận dữ liệu: " + e.getMessage());
        }

        if (product != null) {
            displayProductDetails();
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            finish(); // Đóng màn hình nếu không có dữ liệu
            return;
        }

        setupEvents();
    }

    private void displayProductDetails() {
        tvDetailName.setText(product.getName());
        tvDetailDescription.setText(product.getDescription());
        
        // Load ảnh bằng Glide
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            Glide.with(this)
                 .load(product.getImageUrl())
                 .placeholder(R.drawable.laptop)
                 .error(R.drawable.laptop)
                 .into(imgDetail);
        } else {
            int resId = product.getImageResource() != 0 ? product.getImageResource() : R.drawable.laptop;
            imgDetail.setImageResource(resId);
        }
        
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        tvDetailPrice.setText(format.format(product.getPrice()) + " đ");
        tvDetailCategory.setText("Danh mục: " + product.getCategory());
    }

    private void setupEvents() {
        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> {
                CartManager.addToCart(product);
                Toast.makeText(this, "Đã thêm " + product.getName() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnGoCart != null) {
            btnGoCart.setOnClickListener(v ->
                    startActivity(new Intent(DetailActivity.this, CartActivity.class)));
        }

        if (btnRelated != null) {
            btnRelated.setOnClickListener(v -> {
                Intent intent = new Intent(DetailActivity.this, RelatedProductActivity.class);
                intent.putExtra("category", product.getCategory());
                startActivity(intent);
            });
        }
    }

    // Phương thức xử lý nút quay lại trong XML (android:onClick="onBackPressed")
    public void onBackPressed(View view) {
        finish();
    }
}