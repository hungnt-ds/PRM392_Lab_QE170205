package com.example.lab5_recycleview;

public class Food {
    private String name;
    private int price;
    private String imageUri;
    private String description;

    public Food(String name, int price, String imageUri, String description) {
        this.name = name;
        this.price = price;
        this.imageUri = imageUri;
        this.description = description;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUri() { return imageUri; } // Đổi từ getImageResource() -> getImageUri()
    public String getDescription() { return description; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUri(String imageUri) { // Đổi từ setImageResource() -> setImageUri()
        this.imageUri = imageUri;
    }
}
