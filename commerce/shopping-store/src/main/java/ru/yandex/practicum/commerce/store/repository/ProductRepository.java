package ru.yandex.practicum.commerce.store.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.commerce.store.model.Product;
import ru.yandex.practicum.dto.product.ProductCategory;

/**
 * Repository interface for managing {@link Product} entities.
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {

  Page<Product> findAllByProductCategory(ProductCategory category, Pageable pageable);
}
