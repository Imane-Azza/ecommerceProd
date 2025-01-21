package com.example.ecommerceprod.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceprod.R;
import com.example.ecommerceprod.databinding.FragmentOnBoardingBinding;
import com.example.ecommerceprod.databinding.FragmentSigninBinding;


public class onBoardingFragment extends Fragment {


    private FragmentOnBoardingBinding binding;

    public onBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnBoardingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCreateAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_to_signUpFragment)
        );

        binding.idIgnoreText.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_onBoardingFragment_to_itemProductFragment)
        );
    }
}