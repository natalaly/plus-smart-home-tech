package ru.yandex.practicum.telemetry.analyzer.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

/**
 * запускает цикл опроса и обработки снапшотов;
 * обрабатывать снапшоты и проверять на соответствие условиям сценариев.
 * цикл опроса можно запустить в основном потоке
 */
//TODO
@Component
@RequiredArgsConstructor
@Slf4j
public class SnapshotConsumer implements Runnable {

  private final KafkaConsumer<String, HubEventAvro> hubConsumer;

  @Override
  public void run() {
    // подписка на топики
    // ...
    // цикл опроса
  }

  // ...детали реализации...
}
