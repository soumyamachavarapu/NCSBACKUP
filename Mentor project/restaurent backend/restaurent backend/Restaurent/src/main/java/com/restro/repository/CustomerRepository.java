package com.restro.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restro.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	

	Customer findByUsernameAndPassword(String username, String password);

	Customer findByUsername(String username);

	

	

}
