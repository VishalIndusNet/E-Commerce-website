package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.RatingReq;
import com.indusnet.ECommerce.application.entity.Rating;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingReq req, User user ) throws ProductException;

    List<Rating> getProductRating(Long productId);
}
