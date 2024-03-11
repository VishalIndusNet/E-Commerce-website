package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategory(@RequestParam String category,
                                                               @RequestParam List<String> color,
                                                               @RequestParam List<String>size,
                                                               @RequestParam Integer minPrice,
                                                               @RequestParam Integer maxPrice,
                                                               @RequestParam Integer minDiscount,
                                                               @RequestParam String sort,
                                                               @RequestParam String stock,
                                                               @RequestParam Integer pageNumber,
                                                               @RequestParam Integer pageSize){

      Page<Product> res= productService.findAllProduct(category,color,size,minPrice,maxPrice,minDiscount,sort,stock,pageNumber,pageSize);

      return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable Long productId) throws ProductException {

        Product product= productService.findProductById(productId);

        return new ResponseEntity<>(product,HttpStatus.ACCEPTED);
    }
}
