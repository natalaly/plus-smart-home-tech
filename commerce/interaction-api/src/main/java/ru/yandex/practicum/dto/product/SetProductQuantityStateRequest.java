package ru.yandex.practicum.dto.product;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

/**
 * Request to change the quantity state of a product.
 */
@Data
public class SetProductQuantityStateRequest {

 @NotNull
 private UUID productId;

 @NotNull
 private QuantityState quantityState;

}
