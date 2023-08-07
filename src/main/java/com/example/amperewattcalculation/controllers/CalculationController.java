package com.example.amperewattcalculation.controllers;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.amperewattcalculation.models.PowerStrip;
import com.example.amperewattcalculation.repository.PowerStripRepository;

@Controller
public class CalculationController {

  // PowerStripクラスのフィールドをfinalにする
  private final PowerStripRepository repository;

  public CalculationController(PowerStripRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/")
  public String calculation() {
    return "calculation";
  }

  @GetMapping("/device")
  public String device(@ModelAttribute PowerStrip powerstrip, Model model) {
    model.addAttribute("powerstrips", repository.findAll());
    return "device";
  }

  @GetMapping("/powerstrip/register")
  public String ps_register() {
    return "PowerStrip/register";
  }

  @PostMapping("/powerstrip/register")
  public String ps_register(@ModelAttribute PowerStrip powerstrip) {
    return "device";
  }

  @GetMapping("/powerstrip/edit/{id}")
  public String ps_edit(@PathVariable("id") long ps_id, Model model) {
    // idと一致するデータを取得
    model.addAttribute("powerstrip", repository.findById(ps_id));
    return "PowerStrip/edit";
  }

  @PostMapping("/powerstrip/edit/{id}")
  public String ps_edit2(@PathVariable("id") long ps_id, Model model) {
    // idと一致するデータを取得
    model.addAttribute("powerstrip", repository.findById(ps_id));
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

  // 初期データ
  @PostConstruct
  public void dataInit() {
    PowerStrip Panasonic = new PowerStrip();
    Panasonic.setPs_maker_name("Panasonic");
    Panasonic.setPs_name("電源タップ");
    Panasonic.setPs_code("P01");
    Panasonic.setOutllet_number(3);
    repository.saveAndFlush(Panasonic);

    PowerStrip Apw = new PowerStrip();
    Apw.setPs_maker_name("Apw");
    Apw.setPs_name("電源タップ");
    Apw.setPs_code("APW01");
    Apw.setOutllet_number(3);
    repository.saveAndFlush(Apw);
  }
}
