package com.stadiumfooddelivery.menu;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShoppingCart {

    @NonNull
    Map<MenuItem, Integer> cart = new HashMap<>();

    @Inject
    public ShoppingCart() {

    }

    public synchronized void addToCart(@NonNull MenuItem menuItem) {
        if (cart.containsKey(menuItem)) {
            cart.put(menuItem, cart.get(menuItem) + 1);
        } else {
            cart.put(menuItem, 1);
        }
    }

    public synchronized boolean removeFromCart(MenuItem menuItem) {
        if (cart.containsKey(menuItem)) {
            int count = cart.get(menuItem) - 1;
            if (count == 0) {
                cart.remove(menuItem);
                return true;
            } else {
                cart.put(menuItem, count);
            }
        }
        return false;
    }

    public int getCount(MenuItem menuItem) {
        return cart.containsKey(menuItem) ? cart.get(menuItem) : 0;
    }

    public List<MenuItem> getAddedItems() {
        return new ArrayList<>(cart.keySet());
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public int totalPrice() {
        int res = 0;
        for (Map.Entry<MenuItem, Integer> entry : cart.entrySet()) {
            res += entry.getKey().getPrice() * entry.getValue();
        }
        return res;
    }

    public String totalPriceAsString() {
        return totalPrice() + " " + MenuItem.CURRENCY;
    }
}
