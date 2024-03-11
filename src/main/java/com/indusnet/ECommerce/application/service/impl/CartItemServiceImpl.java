package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.CartItem;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.CartItemException;
import com.indusnet.ECommerce.application.exception.UserException;
import com.indusnet.ECommerce.application.repo.CartItemRepo;
import com.indusnet.ECommerce.application.repo.CartRepo;
import com.indusnet.ECommerce.application.service.CartItemService;
import com.indusnet.ECommerce.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepo cartItemRepo;
    private final UserService userService;
    private final CartRepo cartRepo;

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);
        cartItem.setProductPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountPrice(cartItem.getProduct().getDiscountPrice() * cartItem.getQuantity());

        return cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws  Exception {

        CartItem item= findCartItemById(id);
        User user= userService.findUserById(item.getUserId());

        if(user.getId().equals(userId)){
            item.setQuantity(item.getQuantity());
            item.setProductPrice(item.getQuantity()* item.getProduct().getPrice());
            item.setDiscountPrice(item.getQuantity() * item.getProduct().getDiscountPrice());
        }

        return cartItemRepo.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        CartItem cartItem= cartItemRepo.isCartItemExist(cart,product,size,userId);

        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item= findCartItemById(cartItemId);
        User user= userService.findUserById(item.getUserId());

        User reqUser = userService.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartItemRepo.deleteById(cartItemId);
        } else {
            throw new UserException("you can't remove another users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {

        CartItem cartItem= cartItemRepo.findById(cartItemId).orElseThrow(()->
                new CartItemException("CartItem not found with id: " + cartItemId));

        return cartItem;
    }
}
