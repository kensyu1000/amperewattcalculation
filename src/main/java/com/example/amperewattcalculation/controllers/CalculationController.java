package com.example.amperewattcalculation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculationController {
  @GetMapping("/")
  public String calculation() {
    return "calculation";
  }

  @GetMapping("/device")
  public String device() {
    return "device";
  }
}
