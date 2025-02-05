package com.example.lab3_bai2_customlistview;

import android.net.Uri;

public class Fruit {
    private int imageResource;
    private String name;
    private String description;
    private Uri imageUri; // Thêm dòng này

    public Fruit(int imageResource, String name, String description) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
    }

    public Fruit(Uri imageUri, String name, String description) {
        this.imageUri = imageUri;
        this.name = name;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
