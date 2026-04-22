package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationUserActivity extends AppCompatActivity {

    TextView tvUserName, tvUserEmail;
    Button btnOpenChangePassword, btnLogout;
    LinearLayout navHome, navSearch, navCart, navUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);

        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        btnOpenChangePassword = findViewById(R.id.btnOpenChangePassword);
        btnLogout = findViewById(R.id.btnLogout);

        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navCart = findViewById(R.id.navCart);
        navUser = findViewById(R.id.navUser);

        tvUserName.setText(UserManager.currentName);
        tvUserEmail.setText(UserManager.currentEmail);

        btnOpenChangePassword.setOnClickListener(v ->
                startActivity(new Intent(InformationUserActivity.this, ChangePasswordActivity.class)));

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(InformationUserActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        navHome.setOnClickListener(v ->
                startActivity(new Intent(InformationUserActivity.this, HomeActivity.class)));

        navSearch.setOnClickListener(v ->
                startActivity(new Intent(InformationUserActivity.this, SearchActivity.class)));

        navCart.setOnClickListener(v ->
                startActivity(new Intent(InformationUserActivity.this, CartActivity.class)));

        navUser.setOnClickListener(v -> {
        });
    }
}