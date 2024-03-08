package com.indusnet.ECommerce.application.dto;

import com.indusnet.ECommerce.application.entity.Size;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class CreateProductRequest {

    private String productName;

    private String description;

    private Integer quantity;
    private double price;
    private double discountPercent;
    private double discountPrice;
    private String brand;
    private String color;

    private Set<Size> size= new HashSet<>();

    private String firstLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
}
