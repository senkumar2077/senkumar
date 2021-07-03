package com.shoppingcart.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.bean.Cart;

import com.shoppingcart.service.ShoppingService;

@RestController
@RequestMapping("/products")
public class ShoppingController {
	
    @Autowired
    ShoppingService service;


    @GetMapping
    public Double getProductsPrice(@RequestBody Cart items,Integer qty) {
    	System.out.println("get called:");
        return service.getTotalOrderPrice(items,qty);
    }
}


