package com.indusnet.ECommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemReq {

    private Long productId;
    private String size;
    private int quantity;
    private double price;
}
