package com.example.doan3.Model;

public class Restaurant {
    private boolean bestRestaurant;
    private int categoryId;
    private String description;
    private int id;
    private String imagePath;
    private String name;
    private int star;

    public Restaurant(){

    }

    public Restaurant(boolean bestRestaurant, int star, String name, String imagePath, int id, String description, int categoryId) {
        this.bestRestaurant = bestRestaurant;
        this.star = star;
        this.name = name;
        this.imagePath = imagePath;
        this.id = id;
        this.description = description;
        this.categoryId = categoryId;
    }

    public boolean isBestRestaurant() {
        return bestRestaurant;
    }

    public void setBestRestaurant(boolean bestRestaurant) {
        this.bestRestaurant = bestRestaurant;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
