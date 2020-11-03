package com.ibm.msreskill.cart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product")
public interface ProductFeignClient {

	@RequestMapping(path = "/validateProduct/{productId}",method = RequestMethod.POST)
	public Boolean validateProduct(@PathVariable int productId);
}
