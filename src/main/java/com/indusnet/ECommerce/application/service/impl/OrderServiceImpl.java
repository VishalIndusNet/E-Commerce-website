package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.entity.Address;
import com.indusnet.ECommerce.application.entity.Order;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.OrderException;
import com.indusnet.ECommerce.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(User user, Address address) {

        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long orderId) {
        return null;
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confrimOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public Order deleteOrder(Long orderId) throws OrderException {
        return null;
    }
}
