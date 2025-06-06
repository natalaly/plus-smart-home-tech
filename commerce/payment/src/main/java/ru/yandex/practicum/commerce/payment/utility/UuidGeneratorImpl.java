package ru.yandex.practicum.commerce.payment.utility;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link UuidGenerator} that generates random UUIDs.
 * <p>
 * Generates a new random UUID and logs the generated ID.
 */
@Component
@Slf4j
public class UuidGeneratorImpl implements UuidGenerator {

  @Override
  public UUID generate() {
    final UUID id = UUID.randomUUID();
    log.debug("Generated payment ID : {}.", id);
    return id;
  }
}
