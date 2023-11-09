package com.hikolu.ecommerceapp.service.impl;

import com.hikolu.ecommerceapp.dao.ProductDAO;
import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.model.Product;
import com.hikolu.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return productDAO.saveProduct(product);
    }

    @Override
    public List<ProductDTOMain> getBestSellers() {
        return productDAO.getOwnersASC();
    }

    @Override
    @Transactional
    public String deleteProductById(int id) {

        return productDAO.deleteProductById(id);
    }
}
