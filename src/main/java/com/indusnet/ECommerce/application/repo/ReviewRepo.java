package com.indusnet.ECommerce.application.repo;


import com.indusnet.ECommerce.application.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

    @Query("SELECT r FROM Review r WHERE r.product.id=:productId")
    List<Review> getAllProductsReview(@Param("productId")Long productId);
}
