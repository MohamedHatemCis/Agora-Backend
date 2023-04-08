package com.training.agora.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agora/products")

//Inject the needed classes
@RequiredArgsConstructor

// Permit this url to use these end points
@CrossOrigin(origins = "http://localhost:4200")

public class ProductController {
    private final ProductService productService;
    @PostMapping
    public Boolean saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping(path = "/{cate_id}/{sub_id}")
    public List<Product> getAllProductsByCategoryIdAndSubCategoryId(@PathVariable("cate_id") Long cate_id,
                                                                    @PathVariable("sub_id") Long sub_id){
        return productService.getAllProductsWithCategoryAndSubCategory(cate_id,sub_id);
    }

    @DeleteMapping(path = "/{id}")
    public int deleteProductById(@PathVariable long id){
        return productService.updateProductAvailability(id);
    }
}
