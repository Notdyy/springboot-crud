package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
