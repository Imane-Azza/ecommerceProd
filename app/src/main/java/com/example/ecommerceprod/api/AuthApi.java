package com.example.ecommerceprod.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("/auth/v1/signup")
    Call<AuthResponse> signUp(
            @Header("Authorization") String authorization, //
            @Header("apikey") String apiKey,
            @Body Map<String, Object> userData
    );

    @POST("/auth/v1/token?grant_type=password")
    Call<AuthResponse> signIn(
            @Header("Authorization") String authorization, //
            @Header("apikey") String apiKey,
            @Body Map<String, Object> userData
    );
}
