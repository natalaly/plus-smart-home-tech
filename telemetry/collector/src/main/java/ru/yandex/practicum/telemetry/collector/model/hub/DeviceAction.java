package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.hub.DeviceActionType;

/**
 * Represents an action that a device should perform as part of a scenario. This action includes ID
 * of the device, the action to be performed, and an optional value for the action.
 */
@Setter
@Getter
@ToString
public class DeviceAction {

  @NotBlank
  private String sensorId;

  @NotNull
  private DeviceActionType type;

  private Integer value;

}
