package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if (category == null) category = "Điện tử";

        tvRelatedTitle.setText("Sản phẩm liên quan - " + category);

        relatedProducts = new ArrayList<>();
        adapter = new ProductAdapter(this, relatedProducts);
        listViewRelated.setAdapter(adapter);

        // Gọi API (Interceptor sẽ tự động trả về dữ liệu fake cho phần sản phẩm)
        fetchRelatedProductsFromApi(category);

        listViewRelated.setOnItemClickListener((parent, view, position, id) -> {
            Product product = relatedProducts.get(position);
            Intent intent = new Intent(RelatedProductActivity.this, DetailActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
    }

    private void fetchRelatedProductsFromApi(String category) {
        RetrofitClient.getApiService().getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    relatedProducts.clear();
                    for (Product p : response.body()) {
                        if (p.getCategory().equalsIgnoreCase(category)) {
                            relatedProducts.add(p);
                        }
                    }
                    // Nếu không có cùng danh mục, lấy tạm 2 cái đầu tiên
                    if (relatedProducts.isEmpty() && !response.body().isEmpty()) {
                        relatedProducts.add(response.body().get(0));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_ERROR", "Không thể lấy sản phẩm liên quan");
            }
        });
    }
}