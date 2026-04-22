package com.example.hitc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartManager {
    private static final List<CartItem> cartList = new ArrayList<>();

    public static void addToCart(Product product) {
        for (CartItem item : cartList) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity();
                return;
            }
        }
        cartList.add(new CartItem(product, 1));
    }

    public static List<CartItem> getCartList() {
        return cartList;
    }

    public static void increase(Product product) {
        for (CartItem item : cartList) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity();
                return;
            }
        }
    }

    public static void decrease(Product product) {
        Iterator<CartItem> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == product.getId()) {
                if (item.getQuantity() > 1) {
                    item.decreaseQuantity();
                } else {
                    iterator.remove();
                }
                return;
            }
        }
    }

    public static void remove(Product product) {
        Iterator<CartItem> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == product.getId()) {
                iterator.remove();
                return;
            }
        }
    }

    public static double getGrandTotal() {
        double total = 0;
        for (CartItem item : cartList) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public static void clearCart() {
        cartList.clear();
    }
}