package com.example.ecommerceprod.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.ecommerceprod.api.AuthApi;
import com.example.ecommerceprod.api.AuthResponse;
import com.example.ecommerceprod.api.RetrofitClient;
import com.example.ecommerceprod.models.Status;
import com.example.ecommerceprod.models.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private static final String TAG = "UserViewModel";

    private final String plainApiKey="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9pd2NjcWp5cGdzdGFiYndvYXVsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzY5NDYyODcsImV4cCI6MjA1MjUyMjI4N30.ZVQI70fqDw35WTBoWN2APWD2smThYr8h1sPcGjlAj98";



    private MutableLiveData<User> user = new MutableLiveData<>(new User());
    private MutableLiveData<Status> status = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>("");


    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<Status> getStatus() {
        return status;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void signIn() {
        status.setValue(Status.LOADING);
        AuthApi authApi = RetrofitClient.getInstance().create(AuthApi.class);

        Map<String, Object> signinData = new HashMap<>();
        signinData.put("email", user.getValue().getEmail());
        signinData.put("password", user.getValue().getPassword());

        authApi.signIn("Bearer " + plainApiKey, plainApiKey, signinData).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Sign-in successful: " + response.body());
                    status.setValue(Status.SUCCESS);
                } else {
                    Log.e(TAG, "Sign-in failed: " + response.code());
                    errorMessage.setValue("Invalid email or password");
                    status.setValue(Status.ERROR);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e(TAG, "Sign-in request failed", t);
                errorMessage.setValue("Network error");
                status.setValue(Status.ERROR);
            }
        });
    }

    public void signUp() {
        status.setValue(Status.LOADING);
        AuthApi authApi = RetrofitClient.getInstance().create(AuthApi.class);

        Map<String, Object> signupData = new HashMap<>();
        signupData.put("email", user.getValue().getEmail());
        signupData.put("password", user.getValue().getPassword());

        authApi.signUp("Bearer " + plainApiKey, plainApiKey, signupData).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Sign-up successful: " + response.body());
                    status.setValue(Status.SUCCESS);
                } else {
                    Log.e(TAG, "Sign-up failed: " + response.code());
                    errorMessage.setValue("Email already in use or invalid");
                    status.setValue(Status.ERROR);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e(TAG, "Sign-up request failed", t);
                errorMessage.setValue("Network error");
                status.setValue(Status.ERROR);
            }
        });
    }



}

