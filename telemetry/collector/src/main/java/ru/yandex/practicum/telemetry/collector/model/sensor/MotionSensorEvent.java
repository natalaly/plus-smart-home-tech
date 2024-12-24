package ru.yandex.practicum.telemetry.collector.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.SensorEventType;

/**
 * Represents a motion sensor event.
 */
@Setter
@Getter
@ToString(callSuper = true)
public class MotionSensorEvent extends SensorEvent {

  @NotNull
  private Integer linkQuality;

  @NotNull
  private Boolean motion;

  @NotNull
  private Integer voltage;

  @Override
  public SensorEventType getType() {
    return SensorEventType.MOTION_SENSOR_EVENT;
  }
}
