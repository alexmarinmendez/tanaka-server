package edu.galaxy.tanaka.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.galaxy.tanaka.entities.Product;
import edu.galaxy.tanaka.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findByState(true);
	}

	@Override
	public Optional<Product> findById(Long id) {
//		return productRepository.findById(id);
		return productRepository.findByStateAndId(true, id);
	}

	@Override
	public List<Product> findByNameLike(String name) {
		return productRepository.findByNameLike(name);
	}

	@Override
	public List<Product> findByDescriptionLike(String description) {
		return productRepository.findByDescriptionLike(description);
	}

	@Override
	public Product save(Product product, Boolean state) {
		if (state) 
			product.setState(true);
		else 
			product.setState(false);
		return productRepository.save(product);
	}

}
