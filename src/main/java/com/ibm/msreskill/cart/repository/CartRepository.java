package com.ibm.msreskill.cart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.msreskill.cart.model.CartInfo;

@Repository
public interface CartRepository extends CrudRepository<CartInfo, Integer>{

}
