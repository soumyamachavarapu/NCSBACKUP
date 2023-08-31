package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.feign.Iconsumer;
import com.auth.model.Customer;

@Service
public class LoginService {
	
	@Autowired
	private Iconsumer userrepo;
	
	public String validateUser(Customer uObj) {
		Customer userObj = this.userrepo.getUserByUsername(uObj.getUsername());
		if(userObj != null) {
			if(userObj.getPassword().equals(uObj.getPassword())) {
				return "User Authenticated Successfully!!";
			}else {
				System.out.println("Wrong Password");
				return "Wrong Password";
			}
		}else {
			System.out.println("User Not Found!");
			return "User Not Found!";
		}
	  }

}
