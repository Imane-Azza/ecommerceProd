package com.example.ecommerceprod.api;

import com.example.ecommerceprod.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProductsApi {
    @GET("/rest/v1/products") // Supabase REST API endpoint for the products table
    Call<List<Product>> getProducts(
            @Header("Authorization") String authToken ,
            @Header("apikey") String apiKey         // Your Supabase API Key
    );

    Call<List<Product>> getProductsByCategory(String s, String category);
}
