package ru.yandex.practicum.telemetry.analyzer.mapper;

import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.kafka.telemetry.event.DeviceActionAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioConditionAvro;
import ru.yandex.practicum.telemetry.analyzer.entity.Device;
import ru.yandex.practicum.telemetry.analyzer.entity.DeviceAction;
import ru.yandex.practicum.telemetry.analyzer.entity.Scenario;
import ru.yandex.practicum.telemetry.analyzer.entity.ScenarioCondition;

@UtilityClass
@Slf4j
public class ScenarioMapper {

  public Scenario mapToScenario(final ScenarioAddedEventAvro payload, final String hubId) {
    log.debug("Mapping Scenario {} for hubId {}.", payload.getName(), hubId);

    final Scenario scenario = Scenario.builder()
        .name(payload.getName())
        .hubId(hubId)
        .build();
    final List<ScenarioCondition> conditions = mapToScenarioCondition(payload.getConditions(), scenario);
    final List<DeviceAction> actions = mapToDeviceAction(payload.getActions(), scenario);
    scenario.setConditions(conditions);
    scenario.setActions(actions);
    return scenario;
  }

  private List<ScenarioCondition> mapToScenarioCondition(
      final List<ScenarioConditionAvro> conditions, final Scenario scenario) {
    return conditions.stream()
        .map(conditionAvro -> mapToScenarioCondition(conditionAvro, scenario))
        .toList();
  }

  private ScenarioCondition mapToScenarioCondition(final ScenarioConditionAvro conditionAvro,
                                                   final Scenario scenario) {
    return ScenarioCondition.builder()
        .sensor(Device.builder()
            .hubId(scenario.getHubId())
            .id(conditionAvro.getSensorId())
            .build())
        .type(conditionAvro.getType())
        .operation(conditionAvro.getOperation())
        .value(convertToInteger(conditionAvro.getValue()))
        .scenario(scenario)
        .build();
  }

  private List<DeviceAction> mapToDeviceAction(final List<DeviceActionAvro> actions,
                                               final Scenario scenario) {
    return actions.stream()
        .map(actionAvro -> mapToDeviceAction(actionAvro, scenario))
        .toList();
  }

  private DeviceAction mapToDeviceAction(final DeviceActionAvro action, final Scenario scenario) {
    return DeviceAction.builder()
        .type(action.getType())
        .value(action.getValue())
        .scenario(scenario)
        .sensor(Device.builder()
            .hubId(scenario.getHubId())
            .id(action.getSensorId())
            .build())
        .build();
  }

  private Integer convertToInteger(final Object value) {
    return switch (value) {
      case Integer i -> i;
      case Boolean b -> b ? 1 : 0;
      default -> null;
    };
  }

}
