package com.shopshoe.service.impl;

import com.shopshoe.beans.CartItem;
import com.shopshoe.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements CartService{
    Map<Long, CartItem>  maps= new HashMap<>();

    @Override
    public void addCart(CartItem item){
        CartItem cartItem =maps.get(item.getId());
        if(cartItem == null){
            maps.put(item.getId(),item);
        } else {
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }

    }
    @Override
    public void remove(Long id){
        maps.remove(id);
    }
    @Override
    public CartItem updateCart(Long product_id, int quantity){
        CartItem cartItem=maps.get(product_id);
        cartItem.setQuantity(quantity);
        return cartItem;

    }
    @Override
    public void clear(){
        maps.clear();
    }
    @Override
    public Collection<CartItem> getAllItems(){
        return maps.values();
    }
    @Override
    public int getCount(){
        return maps.values().size();
    }
    @Override
    public int getAmount(){
        return maps.values().stream()
                .mapToInt(item->item.getQuantity()*item.getPrice()).sum();
    }
}
