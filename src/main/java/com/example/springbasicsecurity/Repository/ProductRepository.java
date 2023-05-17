package com.example.springbasicsecurity.Repository;

import com.example.springbasicsecurity.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
