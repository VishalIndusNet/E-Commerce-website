package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.dto.AddItemReq;
import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {


    @Override
    public Cart createCart(User user) {

        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemReq req) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
