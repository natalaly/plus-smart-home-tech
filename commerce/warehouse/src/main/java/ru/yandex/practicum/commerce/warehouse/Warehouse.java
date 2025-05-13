package ru.yandex.practicum.commerce.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "ru.yandex.practicum.commerce.warehouse",
    "ru.yandex.practicum.exception.handler"
})
public class Warehouse {

  public static void main(String[] args) {
    SpringApplication.run(Warehouse.class,args);
  }
}