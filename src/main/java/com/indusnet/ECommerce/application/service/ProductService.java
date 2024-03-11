package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.CreateProductRequest;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.exception.ProductException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface ProductService {

    // admin

    Product createProduct(CreateProductRequest request);

    void deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId,Product req) throws ProductException;

    List<Product> findAllProduct();

    // user

    List<Product> findProductByCategory(String category);

    Product findProductById(Long productId) throws ProductException;

    Page<Product> findAllProduct(String category,List<String> colors,List<String> size,Integer minPrice,Integer maxPrice,
    Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);


}
