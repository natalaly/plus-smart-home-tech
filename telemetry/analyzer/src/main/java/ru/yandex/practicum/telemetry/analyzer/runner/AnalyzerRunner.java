package ru.yandex.practicum.telemetry.analyzer.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.telemetry.analyzer.consumer.HubEventConsumer;
import ru.yandex.practicum.telemetry.analyzer.consumer.SnapshotConsumer;

/**
 * The {@code AnalyzerRunner} class is responsible for starting the two independent consumer
 * processes that handle different types of events in the system.
 *
 * <p>It initializes and runs:
 * <ul>
 *   <li>{@code HubEventConsumer} - Processes events related to adding and removing devices(sensors)
 *       and scenarios at the specified Hub. Runs in a separate thread to operate independently.</li>
 *   <li>{@code SnapshotConsumer} - Handles snapshot data and evaluates resent condition of the  hub sensors readings
 *       against defined scenarios. Runs in the main thread.</li>
 * </ul>
 *
 * <p>By using separate threads for these consumers, the system ensures efficient processing
 * of messages without synchronization overhead.
 */
@Component
@RequiredArgsConstructor
public class AnalyzerRunner implements CommandLineRunner {

  final HubEventConsumer hubEventConsumer;
  final SnapshotConsumer snapshotConsumer;

  @Override
  public void run(String... args) throws Exception {

    Thread hubEventsThread = new Thread(hubEventConsumer);
    hubEventsThread.setName("HubEventHandlerThread");
    hubEventsThread.start();

    snapshotConsumer.run();
  }
}
