package com.ibm.msreskill.cart.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class  ItemInfo {
	
	@Id
	private int itemId;
	private int cartId;
	private int orderId;
	private double price;
	
	public ItemInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemInfo(int itemId, int cartId,int orderId, double price) {
		super();
		this.itemId = itemId;
		this.cartId = cartId;
		this.orderId = orderId;
		this.price = price;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	

}
