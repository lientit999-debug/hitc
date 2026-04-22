package com.example.hitc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Đặt một layout trống hoặc đơn giản để tránh màn hình đen nếu chuyển hướng chậm
        setContentView(android.R.layout.browser_link_context_header) 

        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
