package com.example.ecommerceprod.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerceprod.R;
import com.example.ecommerceprod.ViewModels.UserViewModel;
import com.example.ecommerceprod.databinding.FragmentSigninBinding;

public class signinFragment extends Fragment {

    private FragmentSigninBinding binding;
    private UserViewModel userViewModel;

    public signinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observe LiveData for success or error
        userViewModel.getStatus().observe(getViewLifecycleOwner(), status -> {
            switch (status) {
                case SUCCESS:
//                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Sign in successful!", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).navigate(R.id.action_signinFragment_to_itemProductFragment);
                    break;
                case ERROR:
//                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Sign in failed.", Toast.LENGTH_SHORT).show();
                    break;
                case LOADING:
//                    binding.progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        userViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();


            userViewModel.getUser().getValue().setEmail(email);
            userViewModel.getUser().getValue().setPassword(password);

            userViewModel.signIn();
        });

        binding.idSignInToSignUp.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_signinFragment_to_signupFragment)
        );

    }
}
