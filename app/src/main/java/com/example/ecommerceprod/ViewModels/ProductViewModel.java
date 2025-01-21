package com.example.ecommerceprod.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceprod.api.ProductsApi;
import com.example.ecommerceprod.api.RetrofitClient;
import com.example.ecommerceprod.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    private static final String TAG = "ProductViewModel";

    private final String plainApiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9pd2NjcWp5cGdzdGFiYndvYXVsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzY5NDYyODcsImV4cCI6MjA1MjUyMjI4N30.ZVQI70fqDw35WTBoWN2APWD2smThYr8h1sPcGjlAj98";

    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<List<Product>> filteredProducts = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<List<Product>> getFilteredProducts() {
        return filteredProducts;
    }
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchProducts() {
        ProductsApi productApi = RetrofitClient.getInstance().create(ProductsApi.class);

        productApi.getProducts("Bearer " + plainApiKey, plainApiKey).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body());
                    filteredProducts.setValue(response.body());
                } else {
                    Log.e(TAG, "Failed to fetch products: " + response.code());
                    errorMessage.setValue("Failed to fetch products");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "Error fetching products", t);
                errorMessage.setValue("Network error");
            }
        });
    }


    /**
     * Filter products based on the query. Searches across name, category, and brand.
     *
     * @param query The search query (can be null or empty).
     */
    public void filterProducts(String query) {
        if (products.getValue() == null || query == null || query.isEmpty()) {
            filteredProducts.setValue(products.getValue());
            return;
        }

        List<Product> filteredList = new ArrayList<>();
        for (Product product : products.getValue()) {
            if (product.getName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getCategory().toLowerCase().contains(query.toLowerCase()) ||
                    product.getBrand().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        filteredProducts.setValue(filteredList);
    }


}
