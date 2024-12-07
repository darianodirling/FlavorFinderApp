package com.example.flavorfinderapp.network;

import com.example.flavorfinderapp.models.RecipeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EdamamApiService {
    @GET("api/recipes/v2") // Path after the base URL
    Call<RecipeResponse> getRecipes(
            @Query("type") String type,
            @Query("q") String query,
            @Query("app_id") String appId,
            @Query("app_key") String appKey,
            @Query("diet") String diet,
            @Query("health") String health,
            @Query("cuisineType") String cuisineType,
            @Query("mealType") String mealType,
            @Query("dishType") String dishType
    );
}
