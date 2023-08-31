package com.auth.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.Customer;
import com.auth.service.LoginService;

import java.util.Map;
import java.util.HashMap;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;

@RestController
@RequestMapping("auth/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	
private Map<String,String> map = new HashMap<>();
	
	@Autowired
	private LoginService userService;
	
	@GetMapping("/")
	public String serverStarted() {
		return "Authentication Server Started";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody Customer user){
		try {
			String jwtToken = generateToken(user);
		
			map.put("message", "User Successfully LoggedIn");
			
			map.put("token", jwtToken);
		
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("token", null);
			return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	private String generateToken(Customer uobj)throws ServletException 
	{
		
		String jwtToken= "";
		String flag=userService.validateUser(uobj);
		
		if(flag == "User Not Found!")
			throw new ServletException("Invalid Credentials");
		else {
			jwtToken = Jwts.builder()
					        .setSubject(uobj.getUsername())
					        .setIssuedAt(new Date())
					        .setExpiration(new Date(System.currentTimeMillis() + 3000000))
					        .signWith(SignatureAlgorithm.HS256, "secret key")
					        .compact();
		}
		return jwtToken;
	}
	

}
