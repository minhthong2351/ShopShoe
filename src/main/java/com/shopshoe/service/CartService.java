package com.shopshoe.service;

import com.shopshoe.beans.CartItem;

import java.util.Collection;

public interface CartService {
    void addCart(CartItem item);

    void remove(Long id);

    CartItem updateCart(Long product_id, int quantity);

    void clear();

    Collection<CartItem> getAllItems();

    int getCount();

    int getAmount();
}
