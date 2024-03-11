package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.entity.Order;
import com.indusnet.ECommerce.application.exception.OrderException;
import com.indusnet.ECommerce.application.response.ApiResponse;
import com.indusnet.ECommerce.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orders= orderService.getAllOrder();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmOrder(@PathVariable Long orderId) throws OrderException {
        Order order= orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shipped")
    public ResponseEntity<Order> shippedOrder(@PathVariable Long orderId) throws OrderException {
        Order order= orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliverOrder(@PathVariable Long orderId) throws OrderException {
        Order order= orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) throws OrderException {
        Order order= orderService.canceledOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) throws OrderException {
         orderService.deleteOrder(orderId);

         ApiResponse apiResponse= new ApiResponse();
         apiResponse.setMessage("order delete Successfully");
         apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
