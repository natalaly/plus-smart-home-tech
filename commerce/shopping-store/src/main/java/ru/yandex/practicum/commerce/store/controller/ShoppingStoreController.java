package ru.yandex.practicum.commerce.store.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.commerce.store.service.ShoppingStoreService;
import ru.yandex.practicum.controller.ShoppingStoreOperations;
import ru.yandex.practicum.dto.product.ProductCategory;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.dto.product.ProductPage;
import ru.yandex.practicum.dto.product.SetProductQuantityStateRequest;

/**
 * REST controller for managing products in the shopping store.
 */
@RestController
@RequestMapping("/api/v1/shopping-store")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ShoppingStoreController implements ShoppingStoreOperations {

  private final ShoppingStoreService shoppingStoreService;

  /**
   * Retrieves a paginated list of products filtered by a specific category.
   *
   * @param category the product category
   * @param pageable pagination parameters
   * @return a page of products
   */
  @Override
//  @GetMapping
//  @ResponseStatus(HttpStatus.OK)
  public ProductPage getProductsByCategory(final ProductCategory category, final Pageable pageable) {
    log.info("Received request to fetch products for the category: {}, with pagination: {}.",
        category, pageable);
    final Page<ProductDto> response = shoppingStoreService.getProductsByCategory(category,
        pageable);
    log.info("Returning product list of size: {}", response.getTotalElements());
    return new ProductPage(response);
  }

  /**
   * Retrieves product details by its ID.
   *
   * @param productId the product ID
   * @return the product details
   */
  @Override
//  @GetMapping("/{productId}")
//  @ResponseStatus(HttpStatus.OK)
  public ProductDto getProductById(UUID productId) {
    log.info("Received request to retrieve data of the product with ID: {}.", productId);
    final ProductDto product = shoppingStoreService.getProductById(productId);
    log.info("Returning details of the product {}.", product.getProductName());
    return product;
  }

  /**
   * Creates a new product in the store.
   *
   * @param productDto the product details
   * @return the created product
   */
  @Override
//  @PostMapping
//  @ResponseStatus(HttpStatus.OK)
  public ProductDto addProduct(final ProductDto productDto) {
    log.info("Request received to add a new product: {}.", productDto.getProductName());
    final ProductDto savedProduct = shoppingStoreService.addProduct(productDto);
    log.info("Product {} saved with ID {}.", savedProduct.getProductName(),
        savedProduct.getProductId());
    return savedProduct;
  }

  /**
   * Updates an existing product.
   *
   * @param productDto the updated product details
   * @return the updated product
   */
  @Override
//  @PutMapping
//  @ResponseStatus(HttpStatus.OK)
  public ProductDto updateProduct(final ProductDto productDto) {
    log.info("Request received to update a product with ID {}.", productDto.getProductId());
    final ProductDto updatedProduct = shoppingStoreService.updateProduct(productDto);
    log.info("Product, ID {} updated successfully.", updatedProduct.getProductId());
    return updatedProduct;
  }

  /**
   * Updates the quantity status of a product in the store. (The API is called from the warehouse
   * side.)
   *
   * @param request the request object containing the quantity status update details
   * @return true if the update was successful
   */
  @Override
//  @PutMapping("/quantityState")
//  @ResponseStatus(HttpStatus.OK)
  public boolean updateQuantityState(final SetProductQuantityStateRequest request) {
    log.info("Request received to update quantity state for the product with ID {}.",
        request.getProductId());
    boolean isUpdated = shoppingStoreService.updateQuantityState(request);
    log.info("Product quantity state updated successfully.");
    return isUpdated;
  }

  /**
   * Removes a product from the store's assortment. (Management staff function)
   *
   * @param productId the ID of the product to be removed
   * @return true if the product was successfully removed
   */
  @Override
  @PutMapping("/removeProductFromStore")
  @ResponseStatus(HttpStatus.OK)
  public boolean removeProductFromStore(final UUID productId) {
    log.info("Request received to remove product with ID {} from the store.", productId);
    boolean isRemoved = shoppingStoreService.removeProduct(productId);
    log.info("Product state updated successfully to 'DEACTIVATE'.");
    return isRemoved;
  }

}
