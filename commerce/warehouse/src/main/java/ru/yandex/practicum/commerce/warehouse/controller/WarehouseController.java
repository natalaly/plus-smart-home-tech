package ru.yandex.practicum.commerce.warehouse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.feign.WarehouseOperations;
import ru.yandex.practicum.commerce.warehouse.service.WarehouseService;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddProductToWarehouseRequest;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.dto.warehouse.BookedProductsDto;
import ru.yandex.practicum.dto.warehouse.NewProductInWarehouseRequest;

/**
 * REST controller for managing warehouse operations.
 */
@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WarehouseController implements WarehouseOperations {

  private final WarehouseService warehouseService;

  @Override
  public void addProduct(final NewProductInWarehouseRequest product) {
    log.info("Received request to add a new product ID {} to the warehouse", product.getProductId());
    warehouseService.addNewProduct(product);
    log.info("Product added successfully.");
  }

  @Override
  public void increaseProductQuantity(final AddProductToWarehouseRequest request) {
    log.info("Received request to increase quantity of the product: {}.",request.getProductId());
    warehouseService.increaseProductQuantity(request);
    log.info("Product quantity increased successfully.");
  }

  @Override
  public BookedProductsDto checkStock(final ShoppingCartDto shoppingCart) {
    log.info("Received request to check stock for the products in the shopping cart {}.",
        shoppingCart.getShoppingCartId());
    final BookedProductsDto bookedProducts = warehouseService.checkStock(shoppingCart);
    log.info("Returning general information about shopping cart.");
    return bookedProducts;
  }

  @Override
  public AddressDto getWarehouseAddress() {
    log.info("Received request to get an address for the warehouse");
    final AddressDto address = warehouseService.getAddress();
    log.info("Returning address from the city:{}.", address.getCity());
    return address;
  }
}
