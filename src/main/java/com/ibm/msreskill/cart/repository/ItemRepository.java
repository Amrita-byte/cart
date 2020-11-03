package com.ibm.msreskill.cart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.msreskill.cart.model.ItemInfo;

@Repository
public interface ItemRepository extends CrudRepository<ItemInfo, Integer>{

}
