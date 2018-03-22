package com.skishop.service;

import java.util.HashMap;
import java.util.Map;

import com.skishop.entity.Product;

public class Cart {
	
	public Map<Integer, CartItem> container=new HashMap<Integer, CartItem>();

	public void addCart(Product pro){
		if(container.containsKey(pro.getId())){
			CartItem ci=container.get(pro.getId());
			ci.setCount(ci.getCount()+1);
		}else{
			CartItem ci=new CartItem();
			ci.setProduct(pro);
			ci.setCount(1);
			container.put(pro.getId(), ci);
		}
	}
}
