package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.dto.AddItemReq;
import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.CartItem;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.repo.CartRepo;
import com.indusnet.ECommerce.application.service.CartItemService;
import com.indusnet.ECommerce.application.service.CartService;
import com.indusnet.ECommerce.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final ProductService productService;
    private final CartItemService cartItemService;

    @Override
    public Cart createCart(User user) {

        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepo.save(cart);
    }

    @Override
    public void addCartItem(Long userId, AddItemReq req) throws ProductException {

        Cart cart = cartRepo.findByUserId(userId);
        Product product= productService.findProductById(req.getProductId());

        CartItem isPresent= cartItemService.isCartItemExist(cart,product,req.getSize(),userId);
        if(isPresent==null){
        CartItem cartItem= new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setProductPrice(req.getQuantity() * product.getDiscountPrice());
        cartItem.setUserId(userId);
        cartItem.setSize(req.getSize());

        CartItem createdCartItem= cartItemService.createCartItem(cartItem);
        cart.getCartItems().add(createdCartItem);
        }

    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart cart = cartRepo.findByUserId(userId);

        double totalPrice=0;
        double totalDiscountPrice=0;
        int totalItem=0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice+= cartItem.getProductPrice();
            totalDiscountPrice+= cartItem.getDiscountPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalDiscountPrice);
        cart.setTotalItem(totalItem);

        return cartRepo.save(cart);
    }
}
