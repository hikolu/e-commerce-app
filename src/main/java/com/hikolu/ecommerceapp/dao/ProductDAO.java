package com.hikolu.ecommerceapp.dao;

import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.model.Product;

import java.util.List;

public interface ProductDAO {

    // read
    List<Product> getProducts();

    Product getProductById(int id);

    // get with most owners
    List<ProductDTOMain> getOwnersASC();

    // create/update
    Product saveProduct(Product product);

    //delete
    String deleteProductById(int id);
}
