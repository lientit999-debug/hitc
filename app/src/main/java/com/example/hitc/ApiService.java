package com.example.hitc;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products") // Đường dẫn API của bạn
    Call<List<Product>> getProducts();
}