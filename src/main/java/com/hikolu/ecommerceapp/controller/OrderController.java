package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.model.Order;
import com.hikolu.ecommerceapp.service.OrderService;
import com.hikolu.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {

    // add a service
    private OrderService orderService;
    private ProductService productService;

    // use constructor to inject dependency for service
    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    // expose GET for a form
    @GetMapping
    public String getFormAdd(Model model, @RequestParam("productId") int productId) {

        // create an empty order object
        Order order = new Order();
        order.setProductId(productId);

        // order to the model
        model.addAttribute("order", order);

        // return a page
        return "store/add-order-form";
    }

    // expose POST to add new order
    @PostMapping("/save")
    public String addOrder(@ModelAttribute("order") Order order, Principal principal) {

        // change the id (if was added) to 0, to add a new one and not to update existing one
        order.setOrderId(0);
        order.setUsername(principal.getName());

        // get the price from product and update bill on order
        double priceForOne = productService.getProductById(order.getProductId()).getPrice();
        order.setBill(priceForOne * order.getQuantity());

        // save order
        orderService.saveOrder(order);

        return "redirect:/profile/my-orders";
    }

    // expose DELETE to delete an order
    @GetMapping("/cancel")
    public String cancelOrder(@RequestParam("orderId") int id) {

        // delete the order
        orderService.deleteOrderById(id);

        // redirect
        return "redirect:/profile/my-orders";
    }
}
