package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.CartItem;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.exception.CartItemException;
import com.indusnet.ECommerce.application.exception.UserException;

public interface CartItemService {

    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception;

    CartItem isCartItemExist(Cart cart, Product product,String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws Exception;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
