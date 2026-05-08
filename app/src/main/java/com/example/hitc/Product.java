package com.example.hitc;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // Đảm bảo tính ổn định khi truyền Intent

    private int id;
    private String name;
    @SerializedName("description")
    private String description;
    private double price;
    private String category;
    private int imageResource;
    
    @SerializedName("image_url")
    private String imageUrl;

    public Product() {}

    public Product(int id, String name, String description, double price, String category, int imageResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageResource = imageResource;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getImageResource() { return imageResource; }
    public void setImageResource(int imageResource) { this.imageResource = imageResource; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}