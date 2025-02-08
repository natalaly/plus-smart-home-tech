package ru.yandex.practicum.telemetry.aggregator.repository;

import java.util.Optional;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

/**
 * Repository interface for managing sensor snapshot data.
 * Provides methods for saving and retrieving snapshots based on the hub ID.
 */
public interface SnapshotRepository {

  void save(SensorsSnapshotAvro snapshot);

  Optional<SensorsSnapshotAvro> findByHubId(String hubId);

}
