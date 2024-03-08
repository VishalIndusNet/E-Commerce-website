package com.indusnet.ECommerce.application.repo;

import com.indusnet.ECommerce.application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {


//    @Query("SELECT p FROM Product p" +
//          // "WHERE (p.category.name= :category='')" +
//            " WHERE (:category IS NULL OR p.category.name = :category)" +
//            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR(p.discountPrice BETWEEN :minPrice AND :maxPrice))"+
//            "AND (:minDiscount IS NULL OR p.discountPercent >=:minDiscount)"+
//            "ORDER BY"+
//            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, "+
//            " CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC,"
//    )

    @Query("SELECT p FROM Product p WHERE (:category IS NULL OR p.category.name = :category) " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountPrice BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " + // Note the typo in your entity was used here
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountPrice END DESC")
    List<Product> filterProduct(@Param("category") String category,
                                @Param("minPrice") Integer minPrice,
                                @Param("maxPrice") Integer maxPrice,
                                @Param("minDiscount") Integer minDiscount,
                                @Param("sort") String sort);
}
