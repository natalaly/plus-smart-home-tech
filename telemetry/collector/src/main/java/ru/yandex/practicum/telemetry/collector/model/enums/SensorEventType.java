package ru.yandex.practicum.telemetry.collector.model.enums;

/**
 * Represents different types of sensor events.
 * <p>
 * This enum defines the various event sensor types that can be generated
 * in the telemetry system. Each type corresponds to a specific event
 * related to a sensor, such as motion detection, temperature readings,
 * light level detection, climate data, and switch status.
 */
public enum SensorEventType {

  MOTION_SENSOR_EVENT,
  TEMPERATURE_SENSOR_EVENT,
  LIGHT_SENSOR_EVENT,
  CLIMATE_SENSOR_EVENT,
  SWITCH_SENSOR_EVENT

}
