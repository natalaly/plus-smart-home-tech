package ru.yandex.practicum.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

/**
 * DTO class representing product information.
 */
@Data
@Builder
public class ProductDto {

  private UUID productId;

  @NotBlank
  private String productName;

  @NotBlank
  private String description;

  private String imageSrc;

  @NotNull
  private QuantityState quantityState;

  @NotNull
  private ProductState productState;

  private ProductCategory productCategory;

  @NotNull
  @Digits(integer = 19, fraction = 2)
  @DecimalMin(value = "1.0")
  private BigDecimal price;

}
