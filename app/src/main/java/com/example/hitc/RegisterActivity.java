package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNameRegister, edtEmailRegister, edtPasswordRegister, edtConfirmPasswordRegister;
    Button btnRegister;
    TextView tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNameRegister = findViewById(R.id.edtNameRegister);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtConfirmPasswordRegister = findViewById(R.id.edtConfirmPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoLogin = findViewById(R.id.tvGoLogin);

        btnRegister.setOnClickListener(v -> {
            String name = edtNameRegister.getText().toString().trim();
            String email = edtEmailRegister.getText().toString().trim();
            String password = edtPasswordRegister.getText().toString().trim();
            String confirmPassword = edtConfirmPasswordRegister.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            UserManager.register(name, email, password);
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        tvGoLogin.setOnClickListener(v -> finish());
    }
}