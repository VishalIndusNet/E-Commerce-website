package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.dto.RatingReq;
import com.indusnet.ECommerce.application.dto.ReviewRequest;
import com.indusnet.ECommerce.application.entity.Rating;
import com.indusnet.ECommerce.application.entity.Review;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.service.RatingService;
import com.indusnet.ECommerce.application.service.ReviewService;
import com.indusnet.ECommerce.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {


    private final UserService userService;
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);

        Review review=reviewService.createReview(req,user);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReview(@PathVariable Long productId,
                                                         @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);

        List<Review> review=reviewService.getAllReview(productId);

        return new ResponseEntity<>(review, HttpStatus.ACCEPTED);
    }

}
