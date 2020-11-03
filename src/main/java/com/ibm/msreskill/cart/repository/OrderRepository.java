package com.ibm.msreskill.cart.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.msreskill.cart.model.OrderInfo;

public interface OrderRepository extends CrudRepository<OrderInfo, Integer> {

}
