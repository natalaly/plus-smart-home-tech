package ru.yandex.practicum.telemetry.collector.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.SensorEventType;

/**
 * Represents a Switch Sensor Event,containing information about the current state of the switch.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SwitchSensorEvent extends SensorEvent {

  @NotNull
  private Boolean state;

  @Override
  public SensorEventType getType() {
    return SensorEventType.SWITCH_SENSOR_EVENT;
  }
}
