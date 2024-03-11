package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.entity.Address;
import com.indusnet.ECommerce.application.entity.Order;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.service.OrderService;
import com.indusnet.ECommerce.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization")String jwt) throws Exception {

        User user= userService.findUserProfileByJwt(jwt);
        Order order= orderService.createOrder(user,shippingAddress);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping("/user-order-history")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization")String jwt) throws Exception {

        User user= userService.findUserProfileByJwt(jwt);
        List<Order> order= orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") Long orderId
            ,@RequestHeader("Authorization")String jwt) throws Exception {

        User user= userService.findUserProfileByJwt(jwt);
        Order order= orderService.findOrderById(orderId);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
