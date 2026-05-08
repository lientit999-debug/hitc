package com.example.hitc;

import com.google.gson.Gson;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://your-api-url.com/api/"; // URL thật của bạn
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            // Chặn các yêu cầu đến "products" để trả về dữ liệu mẫu
            Interceptor mockInterceptor = chain -> {
                String url = chain.request().url().toString();
                
                if (url.contains("products")) {
                    // Biến MockData thành JSON
                    String json = new Gson().toJson(MockData.getFakeProducts());
                    
                    return new Response.Builder()
                            .code(200)
                            .message("OK")
                            .request(chain.request())
                            .protocol(Protocol.HTTP_1_1)
                            .body(ResponseBody.create(MediaType.parse("application/json"), json))
                            .addHeader("content-type", "application/json")
                            .build();
                }
                
                // Các API khác (Login, Register...) vẫn chạy ra internet bình thường
                return chain.proceed(chain.request());
            };

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(mockInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}