package edu.galaxy.tanaka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.galaxy.tanaka.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
