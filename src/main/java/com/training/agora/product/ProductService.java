package com.training.agora.product;

import com.training.agora.cart_item.CartItem;
import com.training.agora.cart_item.Cart_ItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

//Inject the needed classes
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Cart_ItemService cartItemService;

    //Set date then save the product
    public Boolean saveProduct(Product product)
    {
        product.setCreated_date(new Date());
        product.setModified_date(new Date());
        Product savedProduct=productRepository.save(product);
        return savedProduct!=null?true:false;
    }

    //Get the products of the specific category and subCategory
    public List<Product>getAllProductsWithCategoryAndSubCategory(long cate_id,long sub_id){
        return productRepository.findAllByCategoryIdAndSubCategoryIdAndAvailable(cate_id,sub_id,true);
    }

    //Get product details with the id
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    //Update the quantity of the ordered products
    @Transactional
    public void updateEachProductAfterOrder(long user_id){
        List<CartItem> items=cartItemService.getAllUserCartItems(user_id);
        for (CartItem item:items) {
            updateQuantityOfProduct(item.getProduct().getId(),item.getProd_quantity());
        }
    }

    //Update the quantity of each product
    @Transactional
    public void updateQuantityOfProduct(long prod_id,int decrementedQuantity){
        productRepository.updateQuantityForEachProduct(prod_id,decrementedQuantity);
    }

    //Set the product as deleted
    @Transactional
    public int updateProductAvailability(long prod_id){
      return  productRepository.updateProductAvailability(prod_id);
    }
}
