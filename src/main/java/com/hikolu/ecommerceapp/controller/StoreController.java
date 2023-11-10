package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.model.ObjectMapper;
import com.hikolu.ecommerceapp.model.Product;
import com.hikolu.ecommerceapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    // expose "/store" endpoint
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
    @GetMapping("/best-sellers")
    public String getBestSellers(Model model) {

        // get products with most owners
        List<ProductDTOMain> products = productService.getBestSellers();

        // add attribute
        model.addAttribute("products", products);

        // return page
        return "/store/best-sellers";
    }

    // expose GET "/store/create" endpoint to show form for adding
    @GetMapping("/store/create")
    public String getFormAddProduct(Model model) {

        // create empty product object
        Product product = new Product();

        // add product to the model
        model.addAttribute("product", product);

        // return the view
        return "/store/addProductForm";
    }

    // expose POST "/store/save" to add a new object
    @PostMapping("/store/create")
    public String save(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {

        // check if it has error
        if (bindingResult.hasErrors()) {
            return "/store/addProductForm";
        } else {

            // set the id to 0 just in case the add an object with id (so that it is not updated)
            product.setProductId(0);

            // save the product
            productService.saveProduct(product);

            // redirect
            return "redirect:/";
        }
    }
}
