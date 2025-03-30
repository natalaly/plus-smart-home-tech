package ru.yandex.practicum.dto.cart;

import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

/**
 * Корзина товаров в онлайн магазине
 */
@Data
@Builder
public class ShoppingCartDto {

  /**
   * Идентификатор корзины в БД
   */
  @NotNull
  private UUID shoppingCartId;

  /**
   *  Отображение идентификатора товара на отобранное количество.
   */
  @NotNull
  private Map<UUID, Long> products;


}
