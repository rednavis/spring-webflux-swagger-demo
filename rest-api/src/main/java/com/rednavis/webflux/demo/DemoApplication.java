package com.rednavis.webflux.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public interface DemoApplication {

  static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
