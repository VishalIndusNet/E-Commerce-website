package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.dto.AddItemReq;
import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.response.ApiResponse;
import com.indusnet.ECommerce.application.service.CartService;
import com.indusnet.ECommerce.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);
        Cart cart= cartService.findUserCart(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemReq req,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(),req);

        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("item added to Cart Successfully");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
