package com.example.st1drawermenu.Cart;

import android.graphics.drawable.Drawable;

public class CartModel {

    private String   coffeeName  = null;
    private String  coffePrice  = null;
    private String  coffeeCount = null;

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffePrice() {
        return coffePrice;
    }

    public void setCoffePrice(String coffePrice) {
        this.coffePrice = coffePrice;
    }

    public String getCoffeeCount() {
        return coffeeCount;
    }

    public void setCoffeeCount(String coffeeCount) {
        this.coffeeCount = coffeeCount;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "coffeeName='" + coffeeName + '\'' +
                ", coffePrice='" + coffePrice + '\'' +
                ", coffeeCount='" + coffeeCount + '\'' +
                '}';
    }

    public CartModel() {
    }

    public CartModel(String coffeeName, String coffePrice, String coffeeCount) {
        this.coffeeName = coffeeName;
        this.coffePrice = coffePrice;
        this.coffeeCount = coffeeCount;
    }
}
