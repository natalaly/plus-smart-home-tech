package ru.yandex.practicum.telemetry.collector.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.enums.SensorEventType;

/**
 * Represents a Climate Sensor Event, containing information about temperature, humidity, and CO2
 * levels.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ClimateSensorEvent extends SensorEvent {

  @NotNull
  private Integer temperatureC;

  @NotNull
  private Integer humidity;

  @NotNull
  private Integer co2Level;

  @Override
  public SensorEventType getType() {
    return SensorEventType.CLIMATE_SENSOR_EVENT;
  }
}
