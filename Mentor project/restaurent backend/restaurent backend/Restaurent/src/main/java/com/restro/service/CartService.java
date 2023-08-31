package com.restro.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restro.Entity.Cart;
import com.restro.Entity.Customer;
import com.restro.Entity.Item;
import com.restro.repository.CartRepository;
import com.restro.repository.CustomerRepository;
import com.restro.repository.ItemRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Cart addCartItem(long cid, long pid, long qty) {

		long quantity = qty;
		Item item = itemRepository.findById(pid).get();
		Customer customer = customerRepository.findById(cid).get();

		Cart cartItem = new Cart();
		cartItem.setCustomer(customer);
		cartItem.setQuantity(quantity);
		cartItem.setItem(item);
		return cartRepository.save(cartItem);
	}

	public List<Cart> getAllCartItemsByACustomer(long cid) {
		Customer customer = customerRepository.findById(cid).get();
		return cartRepository.findByCustomer(customer);
	}

	public Map<String, Boolean> deleteById(long id) {
		cartRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
