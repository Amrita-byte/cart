package com.ibm.msreskill.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.msreskill.cart.model.CartInfo;
import com.ibm.msreskill.cart.model.ItemInfo;
import com.ibm.msreskill.cart.model.OrderInfo;
import com.ibm.msreskill.cart.repository.CartRepository;
import com.ibm.msreskill.cart.repository.ItemRepository;
import com.ibm.msreskill.cart.repository.OrderRepository;
import com.ibm.msreskill.cart.utilities.GenSeq;

@Service
public class CartService {
	
	@Autowired
	ItemInfo itemInfo;
	
	@Autowired
	CartInfo cartInfo;
	
	@Autowired
	OrderInfo orderInfo;
	
	@Autowired
	ProductFeignClient productFeignClient;
	
	@Autowired
	AccountloginFeignClient accountLoginFeignClient;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	public OrderInfo addOrdert() {
		int newOrderId = (int) GenSeq.getNextOrderId();
		orderInfo = new OrderInfo(newOrderId,"abc");
		orderRepo.save(orderInfo);
		return orderRepo.findById(newOrderId).get();
	}
	
	public CartInfo addCart() {
		int newCartId = (int) GenSeq.getNextCartId();
		cartInfo = new CartInfo(newCartId,1);
		cartRepo.save(cartInfo);
		return cartRepo.findById(newCartId).get();
	}

	public String addItems(ItemInfo item) {
		// TODO Auto-generated method stub	
		cartInfo = addCart();
		if (productFeignClient.validateProduct(item.getItemId())) {
			
			if(item.getCartId() == 0 ) {			
				item.setCartId(cartInfo.getCartId());			
				itemRepo.save(item);
			}else {
				cartInfo = cartRepo.findById(item.getCartId()).get();
				cartInfo.setQuantity(cartInfo.getQuantity()+1);
				cartRepo.save(cartInfo);
			}
			return "success";
		}else
			return "Invalid item";		
	}
	
	public String deleteItem(ItemInfo item) {
		// TODO Auto-generated method stub			
			if(item.getCartId() == 0 ) {			
				return "Invalid item";
			}else {
				cartInfo = cartRepo.findById(item.getCartId()).get();
				cartInfo.setQuantity(cartInfo.getQuantity()-1);
				cartRepo.save(cartInfo);
			}
			return "success";		
	}

	public Boolean validateHeaderToken(String jwtToken) {
		// TODO Auto-generated method stub
		return accountLoginFeignClient.validateHeaderToken(jwtToken);
	}

	public List<CartInfo> getAllCarts() {
		// TODO Auto-generated method stub
		List<CartInfo> cartList= new ArrayList<>();
		cartRepo.findAll().forEach(cartList::add);
		return cartList;
	}

	public String clearCart() {
		// TODO Auto-generated method stub
		cartRepo.deleteAll();
		return "success";
	}
}
	

