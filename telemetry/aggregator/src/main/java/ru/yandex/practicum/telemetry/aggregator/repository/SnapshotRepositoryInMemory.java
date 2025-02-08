package ru.yandex.practicum.telemetry.aggregator.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

@Component
public class SnapshotRepositoryInMemory implements SnapshotRepository {

  private final Map<String, SensorsSnapshotAvro> snapshots = new HashMap<>();

  @Override
  public void save(final SensorsSnapshotAvro snapshot) {
    snapshots.put(snapshot.getHubId(), snapshot);
  }

  @Override
  public Optional<SensorsSnapshotAvro> findByHubId(final String hubId) {
    return Optional.ofNullable(snapshots.get(hubId));
  }
}
