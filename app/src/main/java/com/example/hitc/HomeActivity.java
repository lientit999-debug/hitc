package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView listViewProducts;
    LinearLayout btnSearchBox, navHome, navSearch, navCart, navUser;
    ArrayList<Product> productList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listViewProducts = findViewById(R.id.listViewProducts);
        btnSearchBox = findViewById(R.id.btnSearchBox);

        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navCart = findViewById(R.id.navCart);
        navUser = findViewById(R.id.navUser);

        productList = new ArrayList<>();
        productList.add(new Product(1, "Áo thun nam", "Áo cotton mềm mại", 150000, "Áo"));
        productList.add(new Product(2, "Quần jean", "Quần jean xanh trẻ trung", 300000, "Quần"));
        productList.add(new Product(3, "Giày sneaker", "Giày thể thao năng động", 500000, "Giày"));
        productList.add(new Product(4, "Balo", "Balo đi học tiện lợi", 250000, "Phụ kiện"));
        productList.add(new Product(5, "Áo hoodie", "Áo hoodie trẻ trung", 350000, "Áo"));
        productList.add(new Product(6, "Áo sơ mi", "Áo sơ mi lịch lãm", 280000, "Áo"));

        adapter = new ProductAdapter(this, productList);
        listViewProducts.setAdapter(adapter);

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