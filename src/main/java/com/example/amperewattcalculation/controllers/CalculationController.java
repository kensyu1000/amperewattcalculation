package com.example.amperewattcalculation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.amperewattcalculation.models.Item;
import com.example.amperewattcalculation.models.PowerStrip;
import com.example.amperewattcalculation.services.ItemService;
import com.example.amperewattcalculation.services.PowerStripService;

@Controller
public class CalculationController {

  @Autowired
  PowerStripService ps_service;

  @Autowired
  ItemService item_service;

  @GetMapping("/")
  public String calculation(@ModelAttribute PowerStrip powerstrip, Item item, Model model) {
    model.addAttribute("powerstrips", ps_service.findAll());
    model.addAttribute("items", item_service.findAll());
    // model.addAttribute("item_names", item_service.findname(item));
    return "calculation";
  }

  @GetMapping("/device")
  public String device(@ModelAttribute PowerStrip powerstrip, Item item, Model model) {
    // "powerstrips"でhtmlに電源タップの情報を全部渡している
    model.addAttribute("powerstrips", ps_service.findAll());
    model.addAttribute("items", item_service.findAll());
    return "device";
  }

  @PostMapping("/")
  public String calculated_register(@ModelAttribute Item item) {
    item_service.register(item);
    return "redirect:/";
  }
}
