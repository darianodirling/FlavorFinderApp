package com.example.flavorfinderapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flavorfinderapp.models.RecipeResponse;
import com.example.flavorfinderapp.network.EdamamApiService;
import com.example.flavorfinderapp.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;  // For demo text
    private final MutableLiveData<RecipeResponse> recipes;  // For API data

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        recipes = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<RecipeResponse> getRecipes() {
        return recipes;
    }

    public void fetchRecipes(String query) {
        EdamamApiService service = RetrofitClientInstance.getRetrofitInstance()
                .create(EdamamApiService.class);

        Call<RecipeResponse> call = service.getRecipes(
                "public",           // Required: type
                query,              // Search query
                "72ff1572",         // App ID
                "dd3a1785060f6e0e899c8c74fc096b3e", // App Key
                null,               // Optional: diet
                null,               // Optional: health
                null,               // Optional: cuisineType
                null,               // Optional: mealType
                null                // Optional: dishType
        );

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recipes.setValue(response.body());  // Update LiveData with API data
                    Log.d("HomeViewModel", "Recipes fetched successfully");
                } else {
                    Log.e("HomeViewModel", "API call failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.e("HomeViewModel", "Error: " + t.getMessage());
            }
        });
    }
}
