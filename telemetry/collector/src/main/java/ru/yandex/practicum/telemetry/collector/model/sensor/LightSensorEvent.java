package ru.yandex.practicum.telemetry.collector.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.SensorEventType;

/**
 * Represents a Light Sensor Event, containing information about signal quality and light level.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class LightSensorEvent extends SensorEvent {

  @NotNull
  private int linkQuality;

  @NotNull
  private int luminosity;

  @Override
  public SensorEventType getType() {
    return SensorEventType.LIGHT_SENSOR_EVENT;
  }
}
