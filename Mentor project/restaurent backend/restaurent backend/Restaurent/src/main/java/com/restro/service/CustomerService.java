package com.restro.service;

import java.util.List;
import java.util.Optional;
import com.restro.exception.CustomerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restro.Entity.Customer;
import com.restro.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;


	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> findById(long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isEmpty()) {
			throw new CustomerException("Customer not found");
		}
		return customer;
	}

	public Customer editCustomer(long id, Customer customer) {
		Customer oldCustomer = customerRepository.findById(id).get();
		oldCustomer.setUsername(customer.getUsername());
		oldCustomer.setEmail(customer.getEmail());
		return customerRepository.save(oldCustomer);
	}

	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}

	public Customer registerCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer findByUsernameAndPassword(String username, String password) {
		Customer user = customerRepository.findByUsernameAndPassword(username, password);
		return user;
	}

	public void deleteById(long id) {
	    customerRepository.deleteById(id);
	}


	

	

}
