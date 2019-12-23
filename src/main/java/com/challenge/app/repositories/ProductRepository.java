package com.challenge.app.repositories;

import com.challenge.app.models.Company;
import com.challenge.app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findByProductIdAndCompany(Long productId, Company company);

}
