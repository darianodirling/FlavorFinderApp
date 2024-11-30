package com.example.flavorfinderapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorfinderapp.databinding.FragmentHomeBinding;
import com.example.flavorfinderapp.adapters.RecipeAdapter;
import com.example.flavorfinderapp.models.RecipeResponse;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private RecipeAdapter recipeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get references to UI components
        EditText searchInput = binding.searchInput;
        Button searchButton = binding.searchButton;
        RecyclerView recyclerView = binding.recipeList;

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeAdapter = new RecipeAdapter(null); // Start with an empty list
        recyclerView.setAdapter(recipeAdapter);

        // Observe LiveData for recipes and update the RecyclerView
        homeViewModel.getRecipes().observe(getViewLifecycleOwner(), recipeResponse -> {
            if (recipeResponse != null) {
                recipeAdapter.updateRecipes(recipeResponse.getHits());
            }
        });

        // Set up the search button click listener
        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString().trim();
            if (!query.isEmpty()) {
                homeViewModel.fetchRecipes(query); // Trigger API call with user input
            } else {
                Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
