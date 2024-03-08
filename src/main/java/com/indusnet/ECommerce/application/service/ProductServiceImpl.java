package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.CreateProductRequest;
import com.indusnet.ECommerce.application.entity.Category;
import com.indusnet.ECommerce.application.entity.Product;
import com.indusnet.ECommerce.application.exception.ProductException;
import com.indusnet.ECommerce.application.repo.CategoryRepo;
import com.indusnet.ECommerce.application.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final UserService userService;

    @Override
    public Product createProduct(CreateProductRequest request) {

        Category topLevel= categoryRepo.findByName(request.getProductName());

        if(topLevel==null){
            Category topLevelCategory= new Category();
            topLevelCategory.setName(request.getProductName());
            topLevelCategory.setLevel(1);

            topLevel= categoryRepo.save(topLevelCategory);
        }

        Category secondLevel= categoryRepo.findByNameAndParent(request.getProductName(), topLevel.getName());

        if(secondLevel==null){
            Category secondLevelCategory= new Category();
            secondLevelCategory.setName(request.getProductName());
            secondLevelCategory.setLevel(2);
            secondLevelCategory.setParentCategory(topLevel);

            secondLevel = categoryRepo.save(secondLevelCategory);
        }


        Category thirdLevel= categoryRepo.findByNameAndParent(request.getProductName(), secondLevel.getName());

        if(thirdLevel==null){
            Category thirdLevelCategory= new Category();
            thirdLevelCategory.setName(request.getProductName());
            thirdLevelCategory.setLevel(3);
            thirdLevelCategory.setParentCategory(secondLevel);

            thirdLevel = categoryRepo.save(thirdLevelCategory);
        }

        Product product = getProduct(request, thirdLevel);

        return productRepo.save(product);
    }

    private static Product getProduct(CreateProductRequest request, Category thirdLevel) {
        Product product= new Product();
        product.setProductName(request.getProductName());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setDiscountPercent(request.getDiscountPercent());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setSizes(request.getSize());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdLevel);
        return product;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product= findProductById(productId);
        product.getSizes().clear(); //  if product is deleted then we also delete their size
        productRepo.delete(product);
        return "product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product= findProductById(productId);

        if(product.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }
        return productRepo.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
     return productRepo.findById(productId).orElseThrow(()-> new ProductException("product not found with id"+ productId));
    }

    @Override
    public List<Product> findProductByCategory(String category) {

        return null;
    }

    @Override
    public Page<Product> findAllProduct(String category,
                                        List<String> colors,
                                        List<String> size,
                                        Integer minPrice,
                                        Integer maxPrice,
                                        Integer minDiscount,
                                        String sort,
                                        String stock,
                                        Integer pageNumber, Integer pageSize) {

        Pageable pageable= PageRequest.of(pageNumber,pageSize);

        List<Product> products= productRepo.filterProduct(category,minPrice,maxPrice,minDiscount,sort);

        if(!colors.isEmpty()){
            products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .toList();
        }

        if(stock!=null){
        if(stock.equals("in_stock")){
            products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
        }
        else if(stock.equals("out_of_stock")){
            products = products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
        }
        }

        int startIndex= (int)pageable.getOffset();
        int endIndex= Math.min(startIndex+ pageable.getPageSize(),products.size());

        List<Product> pageCount= products.subList(startIndex,endIndex);

        Page<Product> filteredProducts= new PageImpl<>(pageCount,pageable,products.size());

        return filteredProducts;
    }
}
