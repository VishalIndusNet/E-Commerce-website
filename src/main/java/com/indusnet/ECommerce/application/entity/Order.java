package com.indusnet.ECommerce.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "order_id")
    private String orderId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDate orderDate;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address shippingAddress;

    private PaymentDetails paymentDetails = new PaymentDetails();
    private double totalPrice;
    private double totalDiscountedPrice;
    private double discount;
    private String orderStatus;
    private int totalItem;
    private LocalDateTime createdAt;

}
