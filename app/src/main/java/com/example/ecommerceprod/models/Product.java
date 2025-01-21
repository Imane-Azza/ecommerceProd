package com.example.ecommerceprod.models;


import java.io.Serializable;

public class Product implements Serializable {
//public class Product {
    private long id;
    private String brand;
    private String name;
    private String price;
    private String image_link;
    private String description;
    private String category;

    public Product(long id, String brand, String name, String price, String image_link, String description, String category) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.image_link = image_link;
        this.description = description;
        this.category = category;
    }


    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getImageLink() { return image_link; }
    public void setImageLink(String imageLink) { this.image_link = imageLink; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }




}

