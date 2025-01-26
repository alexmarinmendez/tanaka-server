package edu.galaxy.tanaka.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.galaxy.tanaka.entities.Product;
import edu.galaxy.tanaka.repositories.ProductRepository;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	// /api/v1/products/1
	@GetMapping("/{id}")
	public Optional<Product> findById(@PathVariable Long id) {
		return productRepository.findById(id);
	}
	
	@GetMapping("/findById")
	public Optional<Product> findByIdExtra(@RequestParam Long id) {
		return productRepository.findById(id);
	}
	
	@GetMapping("/findByName")
	public List<Product> findByName(@RequestParam String name) {
		return productRepository.findByNameLike("%"+name+"%");
	}
	
	@GetMapping("/findByDescription")
	public List<Product> findByDescription(@RequestParam String text) {
		return productRepository.findByDescriptionLike("%"+text+"%");
	}
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		return productRepository.save(product);
	}

}
