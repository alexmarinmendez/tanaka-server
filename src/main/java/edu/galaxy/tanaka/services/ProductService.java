package edu.galaxy.tanaka.services;

import java.util.List;
import java.util.Optional;

import edu.galaxy.tanaka.entities.Product;

public interface ProductService {

	List<Product> findAll();
	Optional<Product> findById(Long id);
	List<Product> findByNameLike(String name);
	List<Product> findByDescriptionLike(String description);
	Product save(Product product);
	Product softDelete(Product product);
}
