package ru.yandex.practicum.telemetry.analyzer.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.telemetry.analyzer.entity.Scenario;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

  List<Scenario> findByHubId(String hubId);

  Optional<Scenario> findByHubIdAndName(String hubId, String name);

  void deleteByNameAndHuId(String name, String hubId);
}
//TODO
