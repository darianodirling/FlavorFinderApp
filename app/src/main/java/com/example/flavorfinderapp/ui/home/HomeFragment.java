package com.example.flavorfinderapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.flavorfinderapp.databinding.FragmentHomeBinding;
import com.example.flavorfinderapp.models.RecipeResponse;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Observe recipes LiveData
        homeViewModel.getRecipes().observe(getViewLifecycleOwner(), new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse != null) {
                    // Update UI with recipe data
                    Log.d("HomeFragment", "Recipes fetched: " + recipeResponse.getHits().size());
                    // You can update RecyclerView or other UI components here
                }
            }
        });

        // Fetch recipes for "chicken"
        homeViewModel.fetchRecipes("chicken");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
