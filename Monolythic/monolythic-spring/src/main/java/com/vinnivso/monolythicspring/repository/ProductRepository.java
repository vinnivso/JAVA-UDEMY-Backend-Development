package com.vinnivso.monolythicspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinnivso.monolythicspring.model.Product;

@Repository
//First argument to JpaRepository is the type of the entity and the second is the type of the primary key.
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
