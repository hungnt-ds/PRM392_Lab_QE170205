package com.example.lab4_intent;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private int price;
    private int imageResource;
    private String description;

    public Food(String name, int price, int imageResource, String description) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.description = description;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
}

