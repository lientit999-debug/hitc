package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    GridView gridViewProducts;
    LinearLayout btnSearchBox, navHome, navSearch, navCart, navUser;
    ProgressBar progressBar;
    ArrayList<Product> productList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridViewProducts = findViewById(R.id.gridViewProducts);
        btnSearchBox = findViewById(R.id.btnSearchBox);
        progressBar = findViewById(R.id.progressBar);
        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navCart = findViewById(R.id.navCart);
        navUser = findViewById(R.id.navUser);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        if (gridViewProducts != null) {
            gridViewProducts.setAdapter(adapter);
        }

        // Ưu tiên gọi API thật, nếu lỗi sẽ tự động chuyển sang Fake
        fetchProductsFromApi();

        setupNavigation();
    }

    private void fetchProductsFromApi() {
        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);

        RetrofitClient.getApiService().getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (progressBar != null) progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    productList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    useFakeData(); // API trả về lỗi thì dùng Fake
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                if (progressBar != null) progressBar.setVisibility(View.GONE);
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());
                useFakeData(); // Không có mạng thì dùng Fake
            }
        });
    }

    private void useFakeData() {
        Toast.makeText(this, "Đang hiển thị dữ liệu mẫu", Toast.LENGTH_SHORT).show();
        productList.clear();
        productList.addAll(MockData.getFakeProducts());
        adapter.notifyDataSetChanged();
    }

    private void setupNavigation() {
        btnSearchBox.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        navSearch.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        navCart.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));
        navUser.setOnClickListener(v -> startActivity(new Intent(this, InformationUserActivity.class)));
    }
}