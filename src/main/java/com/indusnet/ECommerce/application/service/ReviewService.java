package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.ReviewRequest;
import com.indusnet.ECommerce.application.entity.Review;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;

import java.util.List;

public interface ReviewService {


    Review createReview(ReviewRequest request, User user) throws ProductException;

    List<Review> getAllReview(Long productId);
}
