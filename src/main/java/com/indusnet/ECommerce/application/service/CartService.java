package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.AddItemReq;
import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;

public interface CartService {

    Cart createCart(User user);

    void addCartItem(Long userId, AddItemReq req) throws ProductException;

    Cart findUserCart(Long userId);

}
