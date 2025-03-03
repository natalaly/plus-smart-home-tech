package ru.yandex.practicum.telemetry.collector.producer;

import jakarta.annotation.PreDestroy;
import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

/**
 * {@link KafkaEventProducer} is responsible for producing and sending messages to a Kafka topic.
 * <p>
 * It uses a Kafka {@link Producer} to publish Avro messages and ensures proper resource management
 * by closing the producer when the application shuts down.
 */
@Component
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class KafkaEventProducer implements AutoCloseable {

  private final Producer<String, SpecificRecordBase> producer;

  public void send(final SpecificRecordBase event, final String key, final Instant timestamp,
                   final String topic) {
    log.info("Starting sending message to Kafka. Topic: {}, Key: {}, Payload: {}", topic, key,
        event);
    try {
      final ProducerRecord<String, SpecificRecordBase> record =
          new ProducerRecord<>(topic, null, timestamp.toEpochMilli(), key, event);

      producer.send(record, (metadata, exception) -> {
        if (exception != null) {
          log.error("Failed to send message to Kafka. Topic: {}, Key: {}, Error: {}",
              topic, key, exception.getMessage(), exception);
        } else {
          log.info("Message sent successfully. Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
              metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
        }
      });
    } catch (Exception ex) {
      log.error("Unexpected error occurred during message sending to Kafka.", ex);
      throw new RuntimeException("Error during Kafka message sending.", ex);
    }
  }

  @PreDestroy
  @Override
  public void close() {
    try {
      log.info("Flushing producer buffer.");
      producer.flush();
      log.info("Closing Kafka producer.");
      producer.close();
    } catch (Exception e) {
      log.error("Error during closing Kafka producer", e);
    }
  }
}
