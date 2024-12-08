package com.example.flavorfinderapp.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorfinderapp.R;
import com.example.flavorfinderapp.adapters.RecipeAdapter;
import com.example.flavorfinderapp.models.RecipeResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<RecipeResponse.Hit> favoriteRecipes = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecipeAdapter(favoriteRecipes);
        recyclerView.setAdapter(adapter);

        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore.getInstance()
                .collection("users").document(userId).collection("favorites")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    favoriteRecipes.clear(); // Clear the list before adding new items
                    for (com.google.firebase.firestore.DocumentSnapshot document : querySnapshot.getDocuments()) {
                        RecipeResponse.Recipe recipe = new RecipeResponse.Recipe();
                        recipe.setLabel(document.getString("title"));
                        recipe.setCalories(document.getDouble("calories"));
                        recipe.setImage(document.getString("image"));
                        recipe.setUrl(document.getString("url"));

                        RecipeResponse.Hit hit = new RecipeResponse.Hit();
                        hit.setRecipe(recipe);

                        favoriteRecipes.add(hit);
                    }
                    adapter.notifyDataSetChanged();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to load favorites", Toast.LENGTH_SHORT).show();
                });
    }
}
