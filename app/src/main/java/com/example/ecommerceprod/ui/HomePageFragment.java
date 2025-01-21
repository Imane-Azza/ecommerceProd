package com.example.ecommerceprod.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceprod.R;
import com.example.ecommerceprod.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends Fragment {
    private RecyclerView recyclerView;


    public HomePageFragment() {

    }

    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Setup the menu button and popup menu
        ImageView menuButton = view.findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), v);
            popupMenu.getMenuInflater().inflate(R.menu.bottom_nav_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
            popupMenu.show();
        });

        return view;
    }



    private boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Toast.makeText(requireContext(), "Home selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_categories) {
            Toast.makeText(requireContext(), "Categories selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_brands) {
            Toast.makeText(requireContext(), "Brands selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_favorites) {
            Toast.makeText(requireContext(), "Favorites selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cart) {
            Toast.makeText(requireContext(), "Cart selected", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }
}