package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.hub.ScenarioConditionOperation;
import ru.yandex.practicum.telemetry.collector.model.enums.hub.ScenarioConditionType;

/**
 * Represents a condition for an automated scenario, which includes information about the sensor,
 * the type of condition, the operation to be applied, and the value used in the condition.
 */
@Setter
@Getter
@ToString
public class ScenarioCondition {

  @NotBlank
  private String sensorId;

  @NotNull
  private ScenarioConditionType type;

  @NotNull
  private ScenarioConditionOperation operation;

  private Integer value;

}
