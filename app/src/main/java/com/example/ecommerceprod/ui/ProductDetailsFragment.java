package com.example.ecommerceprod.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.ecommerceprod.R;
import com.example.ecommerceprod.databinding.FragmentProductDetailsBinding;
import com.example.ecommerceprod.models.Product;

import android.widget.ImageView;
import android.widget.TextView;


public class ProductDetailsFragment extends Fragment {

    private FragmentProductDetailsBinding binding;

    private Product product;
    private ImageView productImage;
    private TextView productName, productPrice, productCategory, productDescription;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize view binding
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);

        // Retrieve product from bundle passed via navigation
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
        }

        // Check if the product is not null and then set the data to the views
        if (product != null) {
            // Load the product image with Glide
            Glide.with(requireContext())
                    .load(product.getImageLink())
                    .into(binding.productImage);

            // Set product details to the TextViews
            binding.productName.setText(product.getName());
            binding.productPrice.setText("$" + product.getPrice());
            binding.productCategory.setText(product.getCategory());
            binding.productDescription.setText(product.getDescription());
        }

        // Set up back button click listener
        binding.backbotton.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_productDetailsFragment_to_itemProductFragment)
        );

        // Return the root view from the binding
        return binding.getRoot();
    }


}
