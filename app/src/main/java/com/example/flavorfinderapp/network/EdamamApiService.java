package com.example.flavorfinderapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.flavorfinderapp.models.RecipeResponse; // Import the RecipeResponse class

public interface EdamamApiService {
    @GET("search")
    Call<RecipeResponse> getRecipes(
            @Query("q") String query,          // Search term (e.g., chicken)
            @Query("app_id") String appId,     // Your Edamam app ID
            @Query("app_key") String appKey,   // Your Edamam app key
            @Query("from") int from,           // Start index of results
            @Query("to") int to                // End index of results
    );
}
