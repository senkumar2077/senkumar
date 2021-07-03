package com.shoppingcart.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.shoppingcart.bean.Cart;
import com.shoppingcart.bean.HttpList;
import com.shoppingcart.bean.Product;
import com.shoppingcart.service.ShoppingService;
@Service
public class ShoppingServiceImpl implements ShoppingService {

	private	String[][] products; 
	@Autowired
	private static Environment env;
	private Map<String,Integer> items;
	private String promotionType1Product;
	private String OfferProduct;
	private int discount;
	private String[] promotionType2Products;
		
	public Double getTotalOrderPrice(Cart products,int promotionType) {
        double price = 0D;
        boolean offerEligible=false;
        boolean offerGiven=false;
        this.items=products.getItems();
        int proQty=0;
        
        if(promotionType==1)
        {
        if(this.items.containsKey(promotionType1Product))
        {
        	 proQty=this.items.get(promotionType1Product);      		
        }
      	for (Map.Entry<String, Integer> product : items.entrySet()) {
      		String item=product.getKey();
      		Double prodPrice=getPrice(item);
      		int qty=product.getValue();      		
      		if(product.getKey().equals(OfferProduct)&&offerEligible)
      		{     			
      			if(qty==proQty)
      			{
      				price= price+ ((qty*prodPrice)*discount)/100;
      			}else if(qty> proQty)
      			{	
      				int eligibleQty=qty-proQty;
      			  price= price+(((proQty*prodPrice)*discount)/100)+eligibleQty*prodPrice;
      			  
      			}     			
      		}
      		else {
      			price=price+qty*prodPrice;
      		}      		
      	}
        }else if(promotionType==2) {
        int eligible=0;
        eligible=getPromotion2Eligible(this.items);
        if(eligible==1)
        {
        	String prod=getLowestPriceProduct(this.items);
        	for (Map.Entry<String, Integer> product : items.entrySet()) {
          		String item=product.getKey();
          		Double prodPrice=getPrice(item);
          		int qty=product.getValue();  
          		if(!item.equals(prod))
          		{
          		price=price+prodPrice*qty;
          		}
        	}
        }
        	
        }else
        {
        	for (Map.Entry<String, Integer> product : items.entrySet()) {
          		String item=product.getKey();
          		Double prodPrice=getPrice(item);
          		int qty=product.getValue();  
          		price=price+prodPrice*qty;
        }
        }
      	return price;
        		    
        		}
       	 
	    public int getNumberOfProducts() {
	        return this.products.length;
	    }
	    
	    public Double getPrice(String product) {	
	    	  double sum = 0D;
	        
	    	return 0.0;
	    }
	    public int getPromotion2Eligible(Map<String,Integer> prod)
	    {
	    	int result=0;
	    	int count=0;
	     	for (Map.Entry<String, Integer> product : prod.entrySet()) {
	      		String item=product.getKey();
	      		boolean contains = Arrays.stream(promotionType2Products).anyMatch(item::equals);
	    	if(contains)
	    	{
	    		count++;
	    	}
	     	}
	     	if(count>2)
	     	{
	     		result=1;
	     	}
	     	return result;
	    }
	    public String getLowestPriceProduct(Map<String,Integer> prod)
	    {
	    	Double previousPrice=0.0D;
	    	String lowestProduct="";	    	
	    	
	    	for (Map.Entry<String, Integer> product : items.entrySet()) {
          		String item=product.getKey();
          		Double prodPrice=getPrice(item);
          		if(previousPrice==0.0)
          		{
          			previousPrice=prodPrice;
          			lowestProduct= item;
          		}
          		else if(prodPrice<previousPrice)
          		{
          			previousPrice=prodPrice;
          			lowestProduct= item;
          		}
	    	}
	    	return lowestProduct;
	    }
 
}
