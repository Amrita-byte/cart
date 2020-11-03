package com.ibm.msreskill.cart.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CartInfo {
	
	@Id
	private int cartId;
	private int quantity;
	
	
	public CartInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartInfo(int cartId, int quantity) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int orderId) {
		this.cartId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
