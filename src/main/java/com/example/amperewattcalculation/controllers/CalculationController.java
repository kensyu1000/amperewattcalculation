package com.example.amperewattcalculation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;

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

  @GetMapping("/powerstrip/register")
  public String ps_register() {
    return "PowerStrip/register";
  }

  @GetMapping("/powerstrip/edit")
  public String ps_edit() {
    return "PowerStrip/edit";
  }

  @GetMapping("/item/register")
  public String item_register() {
    return "Item/register";
  }

  @GetMapping("/item/edit")
  public String item_edit() {
    return "Item/edit";
  }
}
