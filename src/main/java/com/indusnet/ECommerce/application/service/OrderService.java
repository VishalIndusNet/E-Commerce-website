package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.entity.Address;
import com.indusnet.ECommerce.application.entity.Order;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.OrderException;

import java.util.List;

public interface OrderService {

    //for user
    Order createOrder (User user, Address address) ;

    Order findOrderById(Long orderId) throws OrderException;

    List<Order> userOrderHistory(Long orderId);

    Order placeOrder(Long orderId) throws OrderException;

    // for admin
    Order confirmedOrder(Long orderId) throws OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order canceledOrder(Long orderId) throws OrderException;

    List<Order> getAllOrder();

    void deleteOrder(Long orderId) throws OrderException;


}
