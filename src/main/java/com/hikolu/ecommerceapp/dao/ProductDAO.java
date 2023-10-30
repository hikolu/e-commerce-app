package com.hikolu.ecommerceapp.dao;

import com.hikolu.ecommerceapp.model.Product;

import java.util.List;

public interface ProductDAO {

    // read
    List<Product> getProducts();

    Product getProductById(int id);

    // create/update
    Product saveProduct(Product product);

    //delete
    String deleteProductById(int id);
}
