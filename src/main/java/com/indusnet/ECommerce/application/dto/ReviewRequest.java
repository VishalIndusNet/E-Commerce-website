package com.indusnet.ECommerce.application.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long productId;
    private String review;
}
