package edu.galaxy.tanaka.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.galaxy.tanaka.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByNameLike(String name);
	List<Product> findByDescriptionLike(String text);
	
	List<Product> findByState(Boolean state);
	
}
