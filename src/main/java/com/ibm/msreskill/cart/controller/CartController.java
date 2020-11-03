package com.ibm.msreskill.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.msreskill.cart.model.CartInfo;
import com.ibm.msreskill.cart.model.ItemInfo;
import com.ibm.msreskill.cart.service.AuditService;
import com.ibm.msreskill.cart.service.CartService;

@RestController
@RefreshScope
public class CartController {
	
	@Autowired
	CartService cartservice;
	
	@Autowired
	AuditService auditservice;
	
	@RequestMapping(path = "/welcome",method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to cart ms";
	}
	
	@RequestMapping(path = "/validateHeaderToken",method = RequestMethod.POST)
	public Boolean validateHeaderToken(@RequestHeader("Authorization") String jwtToken) 
			throws Exception{		
		return cartservice.validateHeaderToken(jwtToken);
	}
	
	@RequestMapping(path = "/addItems",method = RequestMethod.POST)
	public String addItems(@RequestHeader("Authorization") String jwtToken,
			@RequestBody ItemInfo itemInfo) {
		if(cartservice.validateHeaderToken(jwtToken))
			return cartservice.addItems(itemInfo);
		else
			return "Invalid token";
		
	}
	
	@RequestMapping(path = "/deleteItem",method = RequestMethod.POST)
	public String deleteItem(@RequestBody ItemInfo itemInfo) {
		return cartservice.deleteItem(itemInfo);
		
	}
	
	@GetMapping(path = "/checkoutCart", produces = "application/json")
	public ResponseEntity<String> checkoutCart() {
		List<CartInfo> cartList = cartservice.getAllCarts();
		String msg = cartservice.clearCart();
		auditservice.publishAuditEvent( new ResponseEntity<List<CartInfo>>(cartList, HttpStatus.OK) );		
		return new ResponseEntity<>( msg, HttpStatus.OK );
	}

}
