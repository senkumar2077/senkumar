package com.shoppingcart.service;

import java.io.IOException;
import java.util.List;

import com.shoppingcart.bean.Cart;
import com.shoppingcart.bean.HttpList;

public interface ShoppingService {
	
	public Double getTotalOrderPrice(Cart products,int promotionType);

}
