package com.example.flavorfinderapp.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecipeResponse {
    @SerializedName("hits")
    private List<Hit> hits;

    public List<Hit> getHits() {
        return hits;
    }

    public static class Hit {
        @SerializedName("recipe")
        private Recipe recipe;

        public Recipe getRecipe() {
            return recipe;
        }

        // Added setter method for recipe
        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }
    }

    public static class Recipe {
        @SerializedName("label")
        private String label;

        @SerializedName("image")
        private String image;

        @SerializedName("url")
        private String url;

        @SerializedName("calories")
        private double calories;

        @SerializedName("ingredientLines")
        private List<String> ingredientLines;

        @SerializedName("cuisineType")
        private List<String> cuisineType;

        @SerializedName("mealType")
        private List<String> mealType;

        @SerializedName("dishType")
        private List<String> dishType;

        @SerializedName("dietLabels")
        private List<String> dietLabels;

        @SerializedName("yield")
        private int yield;

        // Getters
        public String getLabel() {
            return label;
        }

        public String getImage() {
            return image;
        }

        public String getUrl() {
            return url;
        }

        public double getCalories() {
            return calories;
        }

        public List<String> getIngredientLines() {
            return ingredientLines;
        }

        public List<String> getCuisineType() {
            return cuisineType;
        }

        public List<String> getMealType() {
            return mealType;
        }

        public List<String> getDishType() {
            return dishType;
        }

        public List<String> getDietLabels() {
            return dietLabels;
        }

        public int getYield() {
            return yield;
        }

        // Setters
        public void setLabel(String label) {
            this.label = label;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setCalories(double calories) {
            this.calories = calories;
        }
    }
}
