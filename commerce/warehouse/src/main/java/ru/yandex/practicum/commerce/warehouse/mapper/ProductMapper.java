package ru.yandex.practicum.commerce.warehouse.mapper;

import ru.yandex.practicum.commerce.warehouse.model.Product;
import ru.yandex.practicum.dto.warehouse.NewProductInWarehouseRequest;

/**
 * Mapper interface for converting between product-related DTOs and entity models.
 */
public interface ProductMapper {

  Product toEntity(NewProductInWarehouseRequest request);

}
