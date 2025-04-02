package ru.yandex.practicum.commerce.warehouse.mapper;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.commerce.warehouse.model.Dimension;
import ru.yandex.practicum.commerce.warehouse.model.Product;
import ru.yandex.practicum.dto.warehouse.DimensionDto;
import ru.yandex.practicum.dto.warehouse.NewProductInWarehouseRequest;

@Component
@Slf4j
public class ProductMapperImpl implements ProductMapper {

  @Override
  public Product toEntity(final NewProductInWarehouseRequest request) {
    Objects.requireNonNull(request);
    log.trace("Mapping NewProductInWarehouseRequest {} into the Product.", request);
    Boolean fragile = request.getFragile();
    return Product.builder()
        .productId(request.getProductId())
        .fragile(fragile != null && request.getFragile())
        .dimension(toDimension(request.getDimension()))
        .weight(request.getWeight())
        .quantity(0)
        .build();
  }

  private Dimension toDimension(final DimensionDto dto) {
    Objects.requireNonNull(dto);
    log.trace("Mapping DimensionDto {} into the Dimension.", dto);
    return Dimension.builder()
        .width(dto.getWidth())
        .depth(dto.getDepth())
        .height(dto.getHeight())
        .build();
  }
}
