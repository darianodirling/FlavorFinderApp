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
    }

    public static class Recipe {
        @SerializedName("label")
        private String label;

        @SerializedName("image")
        private String image;

        @SerializedName("url")
        private String url;

        public String getLabel() {
            return label;
        }

        public String getImage() {
            return image;
        }

        public String getUrl() {
            return url;
        }
    }
}
