package com.example.doan3.Model;

public class Food {
    private int id;
    private String name;
    private int categoryId;
    private int restaurantId;

    public Food(int restaurantId, int categoryId, String name, int id) {
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
