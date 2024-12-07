package com.example.flavorfinderapp.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flavorfinderapp.R;
import com.example.flavorfinderapp.models.RecipeResponse;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<RecipeResponse.Hit> recipeList;

    public RecipeAdapter(List<RecipeResponse.Hit> recipeList) {
        this.recipeList = recipeList;
    }

    public void updateRecipes(List<RecipeResponse.Hit> newRecipes) {
        this.recipeList = newRecipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeResponse.Recipe recipe = recipeList.get(position).getRecipe();

        // Bind data to the views
        holder.title.setText(recipe.getLabel());
        holder.calories.setText("Calories: " + Math.round(recipe.getCalories()));
        holder.servings.setText("Servings: " + recipe.getYield());
        holder.cuisine.setText("Cuisine: " + (recipe.getCuisineType() != null && !recipe.getCuisineType().isEmpty() ? recipe.getCuisineType().get(0) : "Unknown"));
        holder.mealType.setText("Meal Type: " + (recipe.getMealType() != null && !recipe.getMealType().isEmpty() ? recipe.getMealType().get(0) : "Unknown"));
        holder.dishType.setText("Dish Type: " + (recipe.getDishType() != null && !recipe.getDishType().isEmpty() ? recipe.getDishType().get(0) : "Unknown"));

        // Set a clickable link
        holder.link.setText("Recipe Link");
        holder.link.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getUrl()));
            holder.itemView.getContext().startActivity(browserIntent);
        });

        // Load image with Glide
        Glide.with(holder.itemView.getContext())
                .load(recipe.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView calories;
        TextView servings;
        TextView cuisine;
        TextView mealType;
        TextView dishType;
        TextView link;
        ImageView image;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipe_title);
            calories = itemView.findViewById(R.id.recipe_calories);
            servings = itemView.findViewById(R.id.recipe_servings);
            cuisine = itemView.findViewById(R.id.recipe_cuisine);
            mealType = itemView.findViewById(R.id.recipe_meal_type);
            dishType = itemView.findViewById(R.id.recipe_dish_type);
            link = itemView.findViewById(R.id.recipe_link);
            image = itemView.findViewById(R.id.recipe_image);
        }
    }
}
