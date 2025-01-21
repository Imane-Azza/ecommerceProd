package com.example.ecommerceprod.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceprod.Adapters.ProductAdapter;
import com.example.ecommerceprod.R;
import com.example.ecommerceprod.ViewModels.ProductViewModel;
import com.example.ecommerceprod.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.fragment_item_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        SearchView searchView = view.findViewById(R.id.searchView);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Set up the ProductAdapter
        productAdapter = new ProductAdapter(requireContext(), new ArrayList<>(), product -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("product", product); // Ensure Product implements Serializable
            NavHostFragment.findNavController(ItemProductFragment.this)
                    .navigate(R.id.action_itemProductFragment_to_productDetailsFragment, bundle);
        });

        recyclerView.setAdapter(productAdapter);

        // Set up ViewModel
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Observe filteredProducts LiveData
        productViewModel.getFilteredProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            }
        });

        productViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Fetch all products
        productViewModel.fetchProducts();

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productViewModel.filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productViewModel.filterProducts(newText);
                return true;
            }
        });
    }

}