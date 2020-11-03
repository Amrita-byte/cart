package com.ibm.msreskill.cart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "accountlogin")
public interface AccountloginFeignClient {
	
	@RequestMapping(value = "/validateToken", method = RequestMethod.POST)
	public Boolean validateHeaderToken(@RequestHeader("Authorization") String jwtToken);

}
