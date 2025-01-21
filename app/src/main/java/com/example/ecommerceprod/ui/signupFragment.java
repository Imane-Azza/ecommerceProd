package com.example.ecommerceprod.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerceprod.R;
import com.example.ecommerceprod.ViewModels.UserViewModel;
import com.example.ecommerceprod.databinding.FragmentSignupBinding;


public class signupFragment extends Fragment {

    private FragmentSignupBinding binding;
    private UserViewModel userViewModel;

    public signupFragment() {
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
        binding = FragmentSignupBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observe LiveData for success or error
        userViewModel.getStatus().observe(getViewLifecycleOwner(), status -> {
            switch (status){
                case SUCCESS:
//                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).navigate(R.id.action_signupFragment_to_itemProductFragment);

                    break;
                case ERROR:
//                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Registration failed.", Toast.LENGTH_SHORT).show();
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

        binding.btnRegister.setOnClickListener(v -> {

            String fullName = binding.etFullName.getText().toString();
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            userViewModel.getUser().getValue().setFullName(fullName);
            userViewModel.getUser().getValue().setEmail(email);
            userViewModel.getUser().getValue().setPassword(password);

            userViewModel.signUp();
        });

        binding.idSignUpToSignIn.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_signupFragment_to_signinFragment)
        );

    }
}