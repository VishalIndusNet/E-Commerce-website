package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.dto.RatingReq;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.entity.Rating;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.repo.RatingRepo;
import com.indusnet.ECommerce.application.service.ProductService;
import com.indusnet.ECommerce.application.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final ProductService productService;
    private final RatingRepo ratingRepo;

    @Override
    public Rating createRating(RatingReq req, User user) throws ProductException {

        Product product= productService.findProductById(req.getProductId());

        Rating rating= new Rating();
        rating.setCreatedAt(LocalDateTime.now());
        rating.setUser(user);
        rating.setRating(req.getRating());

        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {

        return ratingRepo.getAllProductsRating(productId);
    }
}
