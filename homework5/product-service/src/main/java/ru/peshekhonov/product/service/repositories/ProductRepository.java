package ru.peshekhonov.product.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.product.service.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}