package ru.yandex.practicum.telemetry.analyzer.grpc;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest;
import ru.yandex.practicum.grpc.telemetry.hubrouter.HubRouterControllerGrpc;

/**
 * gRPC-клиент для отправки действий на устройства хаба.
 */
@Service
@Slf4j
public class HubRouterClient {

  private final HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient;

  public HubRouterClient(@GrpcClient("hubrouter")
                         HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient) {
    this.hubRouterClient = hubRouterClient;
  }

  /**
   * Отправляет команду устройству через gRPC.
   *
   * @ param sensorId ID сенсора.
   * @ param type     Тип действия.
   * @ param value    Значение (опционально).
   */
  public void send(final DeviceActionRequest request) {

    hubRouterClient.handleDeviceAction(request);
    log.info("Request for device action was sent: hubId = {}, scenarioName = {}, timestamp = {}",
        request.getHubId(), request.getScenarioName(), request.getTimestamp());

  }

}
