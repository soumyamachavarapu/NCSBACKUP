package com.restro.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restro.Entity.Item;
import com.restro.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	public Map<String, Boolean> deleteItem(long id) {
		Item item = itemRepository.findById(id).get();
		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public Item getItemById(long id) {
		return itemRepository.findById(id).get();
	}
	
	public Item editItem(long id,Item item) {
		Item oldItem = itemRepository.findById(id).get();
		oldItem.setName(item.getName());
		oldItem.setCategory(item.getCategory());
		oldItem.setDescription(item.getDescription());
		oldItem.setPrice(item.getPrice());
		oldItem.setImage(item.getImage());
		
		return itemRepository.save(oldItem);
	}
	
	public List<Item> getItemsByCategory(String category) {
		return itemRepository.findByCategory(category);
	}
}
