package com.example.amperewattcalculation.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.amperewattcalculation.models.Item;
import com.example.amperewattcalculation.models.PowerStrip;
import com.example.amperewattcalculation.repository.ItemRepository;
import com.example.amperewattcalculation.repository.PowerStripRepository;
import com.example.amperewattcalculation.services.ItemService;
import com.example.amperewattcalculation.services.PowerStripService;

@Controller
public class CalculationController {

  // PowerStripクラスのフィールドをfinalにする
  private final PowerStripRepository psrepository;
  private final ItemRepository itemrepository;

  @Autowired
  PowerStripService ps_service;

  @Autowired
  ItemService item_service;

  public CalculationController(PowerStripRepository psrepository, ItemRepository itemrepository) {
    this.psrepository = psrepository;
    this.itemrepository = itemrepository;
  }

  @GetMapping("/")
  public String calculation() {
    return "calculation";
  }

  @GetMapping("/device")
  public String device(@ModelAttribute PowerStrip powerstrip, Item item, Model model) {
    model.addAttribute("powerstrips", psrepository.findAll());
    model.addAttribute("items", itemrepository.findAll());
    return "device";
  }
}
