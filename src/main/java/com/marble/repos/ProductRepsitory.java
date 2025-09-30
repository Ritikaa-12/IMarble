package com.marble.repos;

import com.marble.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepsitory extends JpaRepository<Product, Integer> {
}