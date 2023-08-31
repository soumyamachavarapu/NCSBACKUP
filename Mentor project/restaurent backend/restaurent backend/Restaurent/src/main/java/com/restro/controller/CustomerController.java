package com.restro.controller;

import java.util.List;
import com.restro.exception.CustomerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restro.Entity.Customer;
import com.restro.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/customers")
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();
	}

	@GetMapping("/customers/profile/{id}")
	public Customer findById(@PathVariable long id) {
		return customerService.findById(id).get();
	}

	@PutMapping("/customers/edit/profile/{id}")
	public Customer editCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		return customerService.editCustomer(id, customer);
	}
	
	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		Customer uObj = this.customerService.findByUsername(username);
	    
	    if (uObj != null) {
	        return ResponseEntity.ok(uObj);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PostMapping("/register")
	public Customer registerUser(@RequestBody Customer customer) {
		String username = customer.getUsername();
		Customer savedCustomer = null;
		if (username != null && username != "") {
			Customer userObj = customerService.findByUsername(username);
			if (userObj != null) {
				throw new CustomerException("Customer already exists");
			}
		}
		savedCustomer = customerService.registerCustomer(customer);
		return savedCustomer;
	}

	@PostMapping("/login")
	public Customer login(@RequestBody Customer user) {
		String username = user.getUsername();
		String password = user.getPassword();
		Customer loguser = customerService.findByUsernameAndPassword(username, password);
		if (loguser == null) {
			throw new CustomerException("invalid credentials");
		}
		return loguser;
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
	    try {
	        customerService.deleteById(id);
	        return ResponseEntity.ok("Customer deleted successfully");
	    } catch (CustomerException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

	
}
