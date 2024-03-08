package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.CreateProductRequest;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.exception.ProductException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface ProductService {

    Product createProduct(CreateProductRequest request);

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId,Product req) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> findProductByCategory(String category);
    Page<Product> findAllProduct(String category,List<String> colors,List<String> size,Integer minPrice,Integer maxPrice,
    Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);
}
