package com.rednavis.webflux.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class StatusController {

  private static final LocalDateTime DEPLOYMENT_DATE = LocalDateTime.now();

  @GetMapping
  @ResponseBody
  public String status() {
    return "Service is up and running" + DEPLOYMENT_DATE;
  }
}