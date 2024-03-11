package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.dto.ReviewRequest;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.entity.Review;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.repo.ReviewRepo;
import com.indusnet.ECommerce.application.service.ProductService;
import com.indusnet.ECommerce.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ProductService productService;

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {

        Product product= productService.findProductById(request.getProductId());
        Review review= new Review();

        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        review.setReview(request.getReview());
        review.setProduct(product);

        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {

        return reviewRepo.getAllProductsReview(productId);
    }
}
