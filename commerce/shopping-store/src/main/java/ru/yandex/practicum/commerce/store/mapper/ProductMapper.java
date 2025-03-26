package ru.yandex.practicum.commerce.store.mapper;

import ru.yandex.practicum.commerce.store.model.Product;
import ru.yandex.practicum.dto.product.ProductDto;

/**
 * Interface for mapping between Product and ProductDto objects.
 */
public interface ProductMapper {

  Product toEntity(ProductDto productDto);

  ProductDto toDto(Product product);
}
