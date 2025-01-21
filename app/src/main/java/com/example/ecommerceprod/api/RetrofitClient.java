package com.example.ecommerceprod.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class RetrofitClient {
        private static final String BASE_URL = "https://oiwccqjypgstabbwoaul.supabase.co";
        private static Retrofit retrofit;

        public static Retrofit getInstance() {
            if (retrofit == null) {
                // Add logging interceptor
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build();

                // Create Retrofit instance
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient) // Use the OkHttpClient with logging
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }


