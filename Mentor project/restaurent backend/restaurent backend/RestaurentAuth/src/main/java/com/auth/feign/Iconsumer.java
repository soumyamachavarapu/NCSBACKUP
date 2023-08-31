package com.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.auth.model.Customer;



@FeignClient(name="restAuth-service", url="http://localhost:7000")
public interface Iconsumer {
	
	@GetMapping("/getuserbyname/{username}")
	public Customer getUserByUsername(@PathVariable String username);

}
