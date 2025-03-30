package ru.yandex.practicum.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

/**
 * Запрос на изменение количества единиц товара
 */
@Data
@Builder
public class ChangeProductQuantityRequest {

  @NotNull
  private UUID productId;

  @NotNull
  @Positive
  private Long newQuantity;

}
