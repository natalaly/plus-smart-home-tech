package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.hub.HubEventType;

/**
 * Represents an event triggered when a device is removed from the system. This event contains the
 * identifier of the removed device.
 */
@Setter
@Getter
@ToString(callSuper = true)
public class DeviceRemovedEvent extends HubEvent {

  @NotBlank
  private String id;

  @Override
  public HubEventType getType() {
    return HubEventType.DEVICE_REMOVED;
  }
}
