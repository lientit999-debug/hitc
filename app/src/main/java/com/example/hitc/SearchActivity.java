package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText edtSearch;
    TextView tvSearchResult;
    ListView listViewSearch;
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
        allProducts.add(new Product(1, "Laptop Gaming", "Core i9, RTX 4090", 55000000, "Điện tử"));
        allProducts.add(new Product(2, "iPhone 15 Pro Max", "Titan tự nhiên, 256GB", 32000000, "Điện thoại"));
        allProducts.add(new Product(3, "Bàn phím cơ", "Switch Cherry MX, RGB", 2500000, "Linh kiện"));
        allProducts.add(new Product(4, "Chuột không dây", "DPI cao, pin bền", 1200000, "Linh kiện"));
        allProducts.add(new Product(5, "Tai nghe chống ồn", "Âm thanh Hi-Res", 4500000, "Điện tử"));
        allProducts.add(new Product(6, "Màn hình 4K", "144Hz, IPS", 8000000, "Linh kiện"));

        filteredProducts = new ArrayList<>(allProducts);
        adapter = new ProductAdapter(this, filteredProducts);
        listViewSearch.setAdapter(adapter);

        updateResultText();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProduct(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        navHome.setOnClickListener(v ->
                startActivity(new Intent(SearchActivity.this, HomeActivity.class)));

        navSearch.setOnClickListener(v -> {
        });

        navCart.setOnClickListener(v ->
                startActivity(new Intent(SearchActivity.this, CartActivity.class)));

        navUser.setOnClickListener(v ->
                startActivity(new Intent(SearchActivity.this, InformationUserActivity.class)));
    }

    private void filterProduct(String keyword) {
        filteredProducts.clear();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        adapter.notifyDataSetChanged();
        updateResultText();
    }

    private void updateResultText() {
        tvSearchResult.setText("Kết quả tìm kiếm (" + filteredProducts.size() + ")");
    }
}