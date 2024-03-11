package com.indusnet.ECommerce.application.repo;

import com.indusnet.ECommerce.application.entity.Cart;
import com.indusnet.ECommerce.application.entity.CartItem;
import com.indusnet.ECommerce.application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {

    @Query("SELECT ci From CartItem ci Where ci.cart= :cart And ci.product= :product And ci.size=:size And ci.userId=:userId")
    CartItem isCartItemExist(@Param("cart") Cart cart,
                             @Param("product") Product product,
                             @Param("size") String size,
                             @Param("userId") Long userId);

}
