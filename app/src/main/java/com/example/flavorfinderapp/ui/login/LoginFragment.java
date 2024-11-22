package com.example.flavorfinderapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flavorfinderapp.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
//import com.firebase.ui.auth.data.model.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginFragment extends Fragment {

    // Firebase Authentication
    private FirebaseAuth mAuth;

    // Register callback for FirebaseUI result
    private final ActivityResultLauncher<Intent> signInLauncher =
            registerForActivityResult(
                    new FirebaseAuthUIActivityResultContract(),
                    new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                        @Override
                        public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                            handleSignInResult(result);
                        }
                    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Button to launch sign-in
        rootView.findViewById(R.id.login_button).setOnClickListener(view -> startSignInFlow());

        return rootView;
    }

    private void startSignInFlow() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_home_black_24dp)  // Optional: Replace with your app logo
                .setTheme(R.style.Theme_FlavorFinderApp)       // Optional: Customize theme
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void handleSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == getActivity().RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Toast.makeText(getActivity(), "Welcome, " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                // Navigate to Home or Dashboard
            }
        } else {
            // Sign-in failed or user canceled
            Toast.makeText(getActivity(), "Sign-in failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

}
