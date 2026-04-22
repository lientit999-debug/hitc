package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    GridView gridViewProducts;
    LinearLayout btnSearchBox, navHome, navSearch, navCart, navUser;
    ArrayList<Product> productList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridViewProducts = findViewById(R.id.gridViewProducts);
        btnSearchBox = findViewById(R.id.btnSearchBox);

        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navCart = findViewById(R.id.navCart);
        navUser = findViewById(R.id.navUser);

        productList = new ArrayList<>();
        productList.add(new Product(1, "Laptop Gaming", "Core i9, RTX 4090", 55000000, "Điện tử"));
        productList.add(new Product(2, "iPhone 15 Pro Max", "Titan tự nhiên, 256GB", 32000000, "Điện thoại"));
        productList.add(new Product(3, "Bàn phím cơ", "Switch Cherry MX, RGB", 2500000, "Linh kiện"));
        productList.add(new Product(4, "Chuột không dây", "DPI cao, pin bền", 1200000, "Linh kiện"));
        productList.add(new Product(5, "Tai nghe chống ồn", "Âm thanh Hi-Res", 4500000, "Điện tử"));
        productList.add(new Product(6, "Màn hình 4K", "144Hz, IPS", 8000000, "Linh kiện"));

        adapter = new ProductAdapter(this, productList);
        gridViewProducts.setAdapter(adapter);

        btnSearchBox.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, SearchActivity.class)));

        navHome.setOnClickListener(v -> {
        });

        navSearch.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, SearchActivity.class)));

        navCart.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, CartActivity.class)));

        navUser.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, InformationUserActivity.class)));
    }
}