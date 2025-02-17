package ru.yandex.practicum.telemetry.analyzer.dispatcher;

import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

/**
 * Defines the appropriate handler for the hub event.
 */
public interface HubEventDispatcher {

  void dispatch(HubEventAvro value);
}
