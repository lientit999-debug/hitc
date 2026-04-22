package com.example.hitc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private ListView listViewCart;
    private TextView tvTotalCart;
    private Button btnPayment;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        setupCart();
        setupEvents();
    }

    private void initViews() {
        listViewCart = findViewById(R.id.listViewCart);
        tvTotalCart = findViewById(R.id.tvTotalCart);
        btnPayment = findViewById(R.id.btnPayment);
    }

    private void setupCart() {
        adapter = new CartAdapter(this, CartManager.getCartList(), new Runnable() {
            @Override
            public void run() {
                updateTotal();
            }
        });
        listViewCart.setAdapter(adapter);
        updateTotal();
    }

    private void setupEvents() {
        btnPayment.setOnClickListener(v -> {
            if (CartManager.getCartList() == null || CartManager.getCartList().isEmpty()) {
                tvTotalCart.setText("Giỏ hàng đang trống");
                return;
            }
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        updateTotal();
    }

    private void updateTotal() {
        double total = CartManager.getGrandTotal();
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        tvTotalCart.setText("Tổng tiền: " + format.format(total) + " đ");
    }
}