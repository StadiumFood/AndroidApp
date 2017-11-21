package com.stadiumfooddelivery.menu;

import android.support.annotation.NonNull;

public class MenuItem {

    static final String CURRENCY = "\u20BD";

    private final int id;
    @NonNull
    private final String title;
    @NonNull
    private final String description;
    private final int price;
    private int likes;
    private int dislikes;

    public MenuItem(int id, @NonNull String title, @NonNull String description, int price, int likes, int dislikes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceAsString() {
        return price + " " + CURRENCY;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes--;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (id != menuItem.id) return false;
        if (price != menuItem.price) return false;
        if (!title.equals(menuItem.title)) return false;
        return description.equals(menuItem.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price;
        return result;
    }
}
