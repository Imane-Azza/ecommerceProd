package com.example.ecommerceprod.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceprod.R;
import com.example.ecommerceprod.models.Product;
import com.example.ecommerceprod.ui.ProductDetailsFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private final ProductClickListener productClickListener;

    public ProductAdapter(Context context, List<Product> productList, ProductClickListener productClickListener) {
        this.context = context;
        this.productList = productList != null ? productList : new ArrayList<>();
        this.productClickListener = productClickListener;
    }


    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_fragment, parent, false);
        return new ProductViewHolder(view);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView name, price, category,description;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            category = itemView.findViewById(R.id.productCategory);
            description=itemView.findViewById(R.id.productDescription);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        if (product == null) {
            Log.e("ProductAdapter", "Product at position " + position + " is null");
            return; // Skip binding if the product is null
        }

        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());

        // Load product image using Glide
        Glide.with(context)
                .load(product.getImageLink())
                .into(holder.productImage);

        holder.itemView.setOnClickListener(v -> productClickListener.onProductClick(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface ProductClickListener {
        void onProductClick(Product product);
    }

}

