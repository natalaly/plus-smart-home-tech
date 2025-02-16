package ru.yandex.practicum.telemetry.analyzer.consumer;

import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;
import ru.yandex.practicum.telemetry.analyzer.configuration.kafka.AnalyzerTopicsConfig;
import ru.yandex.practicum.telemetry.analyzer.configuration.kafka.consumer.AnalyzerSnapshotConsumerConfig;
import ru.yandex.practicum.telemetry.analyzer.handler.SnapshotHandler;

/**
 * The {@code SnapshotConsumer} class is responsible for consuming snapshot events from a Kafka
 * topic. It continuously polls Kafka for new snapshot records and processes them using a
 * {@code SnapshotHandler}.
 * <p>
 * This consumer subscribes to Kafka topics defined in the {@code AnalyzerTopicsConfig} and
 * processes messages in the main application thread. It listens for sensor snapshot events,
 * evaluates them against predefined conditions, and commits offsets after successful processing.
 * <p>
 * The consumer runs in a loop until stopped, handling exceptions gracefully to ensure proper
 * shutdown and resource cleanup.
 *
 * <h4>Key Responsibilities:</h4>
 * <ul>
 *   <li>Subscribes to Kafka topics containing sensor snapshot data.</li>
 *   <li>Processes incoming records using the {@code SnapshotHandler}.</li>
 *   <li>Commits offsets to maintain message processing state.</li>
 *   <li>Handles shutdown via a shutdown hook.</li>
 * </ul>
 */
@Component
@Slf4j
public class SnapshotConsumer implements Runnable {

  private final KafkaConsumer<String, SensorsSnapshotAvro> consumer;
  private final List<String> topics;
  private final Duration CONSUME_ATTEMPT_TIMEOUT;
  private final SnapshotHandler handler;
  private volatile boolean stopped = false;

  public SnapshotConsumer(final KafkaConsumer<String, SensorsSnapshotAvro> snapshotsConsumer,
                          final AnalyzerTopicsConfig topicConfig,
                          final AnalyzerSnapshotConsumerConfig consumerConfig,
                          final SnapshotHandler handler) {
    this.consumer = snapshotsConsumer;
    this.topics = topicConfig.getSnapshots();
    CONSUME_ATTEMPT_TIMEOUT = Duration.ofMillis(consumerConfig.getConsumeAttemptTimeoutMs());
    this.handler = handler;
    Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
  }

  @Override
  public void run() {
    log.debug("Starting listening for the Snapshot events.");
    try {
      consumer.subscribe(topics);
      log.debug("Consumer {} subscriber for the topics {}.", consumer.getClass().getName(), topics);

      pollLoop();

    } catch (WakeupException ignored) {
      log.warn("WakeupException caught - Consumer shutting down.");
    } catch (Exception e) {
      log.error("Error during event processing", e);
    } finally {
      cleanupResources();
    }
  }

  private void pollLoop() {
    while (!stopped) {
      ConsumerRecords<String, SensorsSnapshotAvro> records = consumer.poll(CONSUME_ATTEMPT_TIMEOUT);
      if (!records.isEmpty()) {
        processRecords(records);
        doCommitOffsets();
      }
    }
  }

  private void processRecords(final ConsumerRecords<String, SensorsSnapshotAvro> records) {
    records.forEach(record ->
        handler.handle(record.value()));
  }

  private void doCommitOffsets() {
    try {
      consumer.commitSync();
      log.debug("Offsets commited successfully.");
    } catch (Exception e) {
      log.warn("Error during committing consumer offsets.", e);
    }
  }

  private void cleanupResources() {
    try {
      consumer.commitSync();
    } catch (Exception e) {
      log.error("Error during resource cleanup.", e);
    } finally {
      log.info("Closing Kafka Consumer {}.", consumer.getClass().getName());
      consumer.close();
    }
  }

  public void shutdown() {
    stopped = true;
    consumer.wakeup();
  }
}
