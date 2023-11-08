package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.model.ObjectMapper;
import com.hikolu.ecommerceapp.model.Product;
import com.hikolu.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StoreController {

    private ProductService productService;

    @Autowired
    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    // expose GET "/" for the main page
    @GetMapping("/")
    public String getMainPage(Model model) {

        // get all products
        List<Product> products = productService.getProducts();

        // map them to the DTO
        List<ProductDTOMain> productDTOs = products.stream()
                .map(ObjectMapper::mapToProductDTO)
                .toList();

        // add attribute to the model
        model.addAttribute("products", productDTOs);

        // return the page
        return "/store/main-page";
    }

    @GetMapping("/store")
    public String getStoreList(Model model) {

        // get all products
        List<Product> products = productService.getProducts();

        // add attribute to the model
        model.addAttribute("products", products);

        // return page
        return "/store/store";
    }

    // expose "/best-sellers" endpoint

    // expose "/store" endpoint
}
