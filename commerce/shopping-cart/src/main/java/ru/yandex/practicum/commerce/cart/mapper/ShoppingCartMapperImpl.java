package ru.yandex.practicum.commerce.cart.mapper;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.commerce.cart.model.ShoppingCart;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;

@Component
@Slf4j
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

//  TODO
  @Override
  public ShoppingCart toEntity(final ShoppingCartDto cartDto) {
    log.debug("Mapping ShoppingCartDto {} to ShoppingCart.", cartDto);
    Objects.requireNonNull(cartDto);
    return null;
  }

  @Override
  public ShoppingCartDto toDto(final ShoppingCart cart) {
    log.debug("Mapping ShoppingCart {} to ShoppingCartDto.", cart);
    Objects.requireNonNull(cart);
    return ShoppingCartDto.builder()
        .shoppingCartId(cart.getCartId())
        .products(cart.getProducts())
        .build();
  }
}
