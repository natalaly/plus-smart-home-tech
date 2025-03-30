package ru.yandex.practicum.commerce.cart.mapper;

import ru.yandex.practicum.commerce.cart.model.ShoppingCart;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;

/**
 * Interface for mapping between ShoppingCart and ShoppingCartDto objects.
 */
public interface ShoppingCartMapper {

  ShoppingCart toEntity(ShoppingCartDto cartDto);

  ShoppingCartDto toDto(ShoppingCart cart);
}
