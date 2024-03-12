package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.dto.RatingReq;
import com.indusnet.ECommerce.application.entity.Rating;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.service.RatingService;
import com.indusnet.ECommerce.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ratings")
public class RatingController {

    private final UserService userService;
    private final RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingReq req,
                                               @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);

        Rating rating=ratingService.createRating(req,user);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId,
                                                         @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);

        List<Rating> rating=ratingService.getProductRating(productId);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }
}
