package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnGoLogin = findViewById(R.id.btnGoLogin);
        Button btnGoRegister = findViewById(R.id.btnGoRegister);

        if (btnGoLogin != null) {
            btnGoLogin.setOnClickListener(v ->
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
        }

        if (btnGoRegister != null) {
            btnGoRegister.setOnClickListener(v ->
                    startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class)));
        }
    }
}
