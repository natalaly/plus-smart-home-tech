package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.hub.HubEventType;

/**
 * Represents an event triggered when a scenario is removed from the system. This event contains
 * information about the name of the removed scenario.
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ScenarioRemovedEvent extends HubEvent {

  @NotBlank
  @Size(min = 3)
  private String name;

  @Override
  public HubEventType getType() {
    return HubEventType.SCENARIO_REMOVED;
  }
}
