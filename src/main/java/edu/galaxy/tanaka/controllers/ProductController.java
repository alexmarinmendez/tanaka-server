package edu.galaxy.tanaka.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.galaxy.tanaka.entities.Product;
import edu.galaxy.tanaka.repositories.ProductRepository;
import edu.galaxy.tanaka.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findAll() {
//		return productRepository.findAll();
//		return productRepository.findByState(true);
//		return productRepository.findAllCustomSQL();
//		return productRepository.findAllCustomJPQL();
		return productService.findAll();
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
	
	@PatchMapping("/{id}")
	public Product updateStock(@PathVariable Long id, @RequestBody Product product) {
		Optional<Product> optProduct = productRepository.findById(id);
		if (optProduct.isPresent()) {
			Product oProduct = optProduct.get();
			oProduct.setStock(product.getStock());
			return productRepository.save(oProduct);
		} else {
			throw new RuntimeException("Producto not found");
		}
	}
	
	
//	@DeleteMapping("/{id}")
//	public void hardDelete(@PathVariable Long id) {
//		productRepository.deleteById(id);
//	}
	
	@DeleteMapping("/{id}")
	public void softDelete(@PathVariable Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		if (optProduct.isPresent()) {
			Product oProduct = optProduct.get();
			oProduct.setState(false);
			productRepository.save(oProduct);
		} else {
			throw new RuntimeException("Product not found");
		}
	}
	

}
