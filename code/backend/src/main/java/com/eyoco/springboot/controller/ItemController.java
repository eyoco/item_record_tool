package com.eyoco.springboot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;

import com.eyoco.springboot.exception.ResourceNotFoundException;
import com.eyoco.springboot.model.Item;
import com.eyoco.springboot.repository.ItemRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/items")
	public List<Item> getAllItems(){
		return itemRepository.findAll();
	}
	
	@PostMapping("/items")
	public Item AddItem(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
		return ResponseEntity.ok(item);
	}
	
	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails){
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
		
		item.setName(itemDetails.getName());
		item.setLocation(itemDetails.getLocation());
		item.setDescription(itemDetails.getDescription());
		
		Item updatedItem = itemRepository.save(item);
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable Long id){
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
		
		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
