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

        tvRelatedTitle.setText(getString(R.string.related_title_prefix) + category);

        relatedProducts = new ArrayList<>();

        if (category.equals("Điện tử")) {
            relatedProducts.add(new Product(10, "Loa Bluetooth Marshall", "Âm thanh cực đỉnh", 5500000, "Điện tử", R.drawable.chongon));
            relatedProducts.add(new Product(11, "Smartwatch Series 9", "Theo dõi sức khỏe", 9000000, "Điện tử", R.drawable.ic_launcher_foreground));
        } else if (category.equals("Điện thoại")) {
            relatedProducts.add(new Product(12, "Ốp lưng MagSafe", "Bảo vệ tối ưu", 1200000, "Điện thoại", R.drawable.prm));
            relatedProducts.add(new Product(13, "Sạc dự phòng 20000mAh", "Sạc nhanh 22.5W", 800000, "Điện thoại", R.drawable.prm));
        } else if (category.equals("Linh kiện")) {
            relatedProducts.add(new Product(14, "RAM DDR5 32GB", "Bus 5600MHz", 3500000, "Linh kiện", R.drawable.banphim));
            relatedProducts.add(new Product(15, "SSD NVMe 1TB", "Tốc độ đọc 7000MB/s", 2800000, "Linh kiện", R.drawable.laptop));
        } else {
            relatedProducts.add(new Product(16, "Cáp HDMI 2.1", "Hỗ trợ 8K", 450000, "Phụ kiện", R.drawable.manhinhk));
            relatedProducts.add(new Product(17, "Túi chống sốc", "Vải cao cấp", 600000, "Phụ kiện", R.drawable.laptop));
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