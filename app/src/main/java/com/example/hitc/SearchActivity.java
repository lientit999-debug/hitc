package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText edtSearch;
    TextView tvSearchResult;
    ListView listViewSearch;
    ProgressBar progressBarSearch;
    LinearLayout navHome, navSearch, navCart, navUser;

    ArrayList<Product> allProducts, filteredProducts;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtSearch = findViewById(R.id.edtSearch);
        tvSearchResult = findViewById(R.id.tvSearchResult);
        listViewSearch = findViewById(R.id.listViewSearch);
        
        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navCart = findViewById(R.id.navCart);
        navUser = findViewById(R.id.navUser);

        allProducts = new ArrayList<>();
        filteredProducts = new ArrayList<>();
        adapter = new ProductAdapter(this, filteredProducts);
        listViewSearch.setAdapter(adapter);

        // Gọi API (đã được fake ở cấp độ network)
        fetchDataFromApi();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProduct(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        navHome.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        navCart.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));
        navUser.setOnClickListener(v -> startActivity(new Intent(this, InformationUserActivity.class)));
    }

    private void fetchDataFromApi() {
        RetrofitClient.getApiService().getProducts().enqueue(new retrofit2.Callback<java.util.List<Product>>() {
            @Override
            public void onResponse(retrofit2.Call<java.util.List<Product>> call, retrofit2.Response<java.util.List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allProducts.clear();
                    allProducts.addAll(response.body());
                    filterProduct(edtSearch.getText().toString());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<java.util.List<Product>> call, Throwable t) {
                // Nếu lỗi network thật, dùng MockData làm dự phòng
                allProducts.clear();
                allProducts.addAll(MockData.getFakeProducts());
                filterProduct(edtSearch.getText().toString());
            }
        });
    }

    private void filterProduct(String keyword) {
        filteredProducts.clear();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        adapter.notifyDataSetChanged();
        if (tvSearchResult != null) {
            tvSearchResult.setText("Kết quả tìm kiếm (" + filteredProducts.size() + ")");
        }
    }
}