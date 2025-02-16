package ru.yandex.practicum.telemetry.analyzer.consumer;

import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

//TODO - delete class
@Slf4j
public abstract class BaseKafkaConsumer<K, V> implements Runnable {

  protected final KafkaConsumer<K, V> consumer;
  protected final List<String> topics;
  protected final Duration CONSUME_ATTEMPT_TIMEOUT;


  protected BaseKafkaConsumer(final KafkaConsumer<K, V> consumer, final List<String> topics,
                              final Duration consumeAttemptTimeout) {
    this.consumer = consumer;
    this.topics = topics;
    CONSUME_ATTEMPT_TIMEOUT = consumeAttemptTimeout;
  }

  @Override
  public void run() {
    log.debug("Starting listening for the HubEvents.");
    Runtime.getRuntime().addShutdownHook(new Thread(consumer::wakeup));
    try {
      consumer.subscribe(topics);
      log.debug("Consumer {} subscribed for the topics {}.", consumer.getClass().getName(), topics);

      pollLoop();

    } catch (WakeupException ignores) {
      log.warn("WakeupException caught - Consumer shutting down.");
    } catch (Exception e) {
      log.error("Error during event processing", e);
    } finally {
      cleanupResources();
    }
  }

  private void pollLoop() {
    while (true) {
      ConsumerRecords<K, V> records = consumer.poll(CONSUME_ATTEMPT_TIMEOUT);
      if (!records.isEmpty()) {
        processRecords(records);
        doCommitOffsets();
      }
    }
  }

  protected abstract void processRecords(ConsumerRecords<K, V> records);

  private void doCommitOffsets() {
    try {
      consumer.commitSync();
      log.debug("Offsets committed successfully.");
    } catch (Exception e) {
      log.warn("Error during committing consumer offsets", e);
    }
  }

  private void cleanupResources() {
    try {
      consumer.commitSync();
    } catch (Exception e) {
      log.error("Error during resource cleanup", e);
    } finally {
      log.info("Closing Kafka consumer {}.", consumer.getClass().getName());
      consumer.close();
    }
  }
}
