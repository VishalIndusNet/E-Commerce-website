package com.indusnet.ECommerce.application.dto;

import lombok.Data;

@Data
public class RatingReq {
    private Long productId;
    private double rating;
}
