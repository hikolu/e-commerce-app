package com.hikolu.ecommerceapp.dao.impl;

import com.hikolu.ecommerceapp.dao.ProductDAO;
import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.model.ObjectMapper;
import com.hikolu.ecommerceapp.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    // define a field for entity manager
    private EntityManager entityManager;

    // define constructor
    @Autowired
    public ProductDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> getProducts() {

        // define query
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);

        // get results
        List<Product> result = query.getResultList();

        // return results
        return result;
    }

    @Override
    public Product getProductById(int id) {

        // get product from database
        Product product = entityManager.find(Product.class, id);

        // return product
        return product;
    }

    @Override
    public List<ProductDTOMain> getOwnersASC() {

        // setup query
        Query query = entityManager.createQuery("from Product p order by p.owners asc");

        // get results
        List<Product> results = query.getResultList();

        // limit only first 5 results
        List<ProductDTOMain> dtoMains = results.stream()
                .limit(5)
                .map(ObjectMapper::mapToProductDTO)
                .toList();

        // return results
        return dtoMains;
    }

    @Override
    public Product saveProduct(Product product) {

        // save product in the database
        Product dbProduct = entityManager.merge(product);

        // return updated product
        return dbProduct;
    }

    @Override
    public String deleteProductById(int id) {

        // find product in the database
        Product dbProduct = entityManager.find(Product.class, id);

        // delete product
        entityManager.remove(dbProduct);

        // return the confirmation
        return "Deleted product with id - " + id;
    }
}
