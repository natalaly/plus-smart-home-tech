package ru.yandex.practicum.dto.converter;

import ru.yandex.practicum.dto.product.QuantityState;

public class QuantityStateConverter extends StringToEnumConverter<QuantityState> {

  public QuantityStateConverter(Class<QuantityState> enumType) {
    super(enumType);
  }

}
