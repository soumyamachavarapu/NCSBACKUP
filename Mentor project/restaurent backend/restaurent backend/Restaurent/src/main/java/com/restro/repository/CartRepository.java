package com.restro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restro.Entity.Cart;
import com.restro.Entity.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	List<Cart> findByCustomer(Customer customer);

}
