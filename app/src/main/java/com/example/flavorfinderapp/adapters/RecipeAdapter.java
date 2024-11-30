package com.example.flavorfinderapp.adapters;

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
        holder.title.setText(recipe.getLabel());
        holder.calories.setText("Calories: " + recipe.getCalories());
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
        ImageView image;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipe_title);
            calories = itemView.findViewById(R.id.recipe_calories);
            image = itemView.findViewById(R.id.recipe_image);
        }
    }
}
