package com.indusnet.ECommerce.application.repo;

import com.indusnet.ECommerce.application.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {


  }
