package com.indusnet.ECommerce.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;


    private String productName;

    private String description;

    private Integer quantity;
    private double price;
    private double discountPersent;
    private double discountPrice;
    private String brand;
    private String color;

    @Embedded
    @ElementCollection
    private Set<Size> sizes= new HashSet<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings= new ArrayList<>();


    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews= new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}
