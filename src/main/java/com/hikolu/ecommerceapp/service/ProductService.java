package com.hikolu.ecommerceapp.service;

import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.model.Product;

import java.util.List;

public interface ProductService {

    // get all products
    List<Product> getProducts();

    // get product by id
    Product getProductById(int id);

    // create/update product
    Product saveProduct(Product product);

    // get best sellers
    List<ProductDTOMain> getBestSellers();

    // delete product
    String deleteProductById(int id);
}
