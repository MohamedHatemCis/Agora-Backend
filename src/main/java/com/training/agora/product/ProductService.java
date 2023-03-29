package com.training.agora.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Boolean saveProduct(Product product)
    {
        product.setCreated_date(new Date());
        product.setModified_date(new Date());
        Product savedProduct=productRepository.save(product);
        return savedProduct!=null?true:false;
    }

    public List<Product>getAllProductsWithCategoryAndSubCategory(long cate_id,long sub_id){
        return productRepository.findAllByCategoryIdAndSubCategoryId(cate_id,sub_id);
    }
    public List<Product>getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }
}
