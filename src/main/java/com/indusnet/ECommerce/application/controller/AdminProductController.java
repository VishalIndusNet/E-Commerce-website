package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.dto.CreateProductRequest;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.response.ApiResponse;
import com.indusnet.ECommerce.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request){

        Product product= productService.createProduct(request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {

         productService.deleteProduct(productId);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("product deleted Successfully");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products= productService.findAllProduct();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req ,@PathVariable Long productId) throws ProductException {

        Product product= productService.updateProduct(productId,req);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody CreateProductRequest[] req) throws ProductException {

        for(CreateProductRequest product: req){
            productService.createProduct(product);
        }

        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("product Creates Successfully");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }
}
