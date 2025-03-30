package ru.yandex.practicum.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.yandex.practicum.dto.cart.ChangeProductQuantityRequest;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;

/**
 * API для обеспечения работы корзины онлайн магазина
 */
public interface ShoppingCartOperations {

  /**
   * Получить актуальную корзину для авторизованного пользователя.
   * @param username авторизованный пользователь
   * @return Ранее созданная или новая, в случае ранее созданной, корзина в онлайн магазине
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  ShoppingCartDto getShoppingCart(@RequestParam @NotBlank String username);

  /**
   * Добавить товар в корзину.
   * @param products Отображение идентификатора товара на отобранное количество.
   * @param username авторизованный пользователь
   * @return Корзина товаров с изменениями
   */
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  ShoppingCartDto addProductToCart(@RequestBody @NotEmpty Map<UUID,  @NotNull @Positive Long> products,
                                   @RequestParam @NotBlank String username);

  /**
   * Деактивация корзины товаров для пользователя.
   * @param username авторизованный пользователь
   */
  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  void deactivateCurrentCart(@RequestParam @NotBlank String username);

  /**
   * Изменить состав товаров в корзине, т.е. удалить другие, те, которые не указаны.
   *( Retain only products that are in the provided productIds list.)
   * @param username авторизованный пользователь
   * @param products Отображение идентификатора товара на отобранное количество.
   * @return Корзина товаров с изменениями
   */
  @PutMapping("/remove")
  @ResponseStatus(HttpStatus.OK)
  ShoppingCartDto removeProductsFromCart(@RequestParam  @NotBlank String username,
                                 @RequestBody Set<@NotNull UUID> products);

  /**
   * Изменить количество товаров в корзине
   * @param username авторизованный пользователь
   * @param request Запрос на изменение количества единиц товара
   * @return Корзина товаров с изменениями
   */
  @PutMapping("/change-quantity")
  @ResponseStatus(HttpStatus.OK)
  ShoppingCartDto changeQuantity(@RequestParam @NotBlank String username,
                                 @RequestBody @Valid ChangeProductQuantityRequest request);
}
