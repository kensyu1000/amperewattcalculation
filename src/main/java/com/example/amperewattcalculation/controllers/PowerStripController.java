package com.example.amperewattcalculation.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.amperewattcalculation.models.PowerStrip;
import com.example.amperewattcalculation.repository.PowerStripRepository;
import com.example.amperewattcalculation.services.PowerStripService;

@Controller
public class PowerStripController {

  private final PowerStripRepository psrepository;

  @Autowired
  PowerStripService ps_service;

  public PowerStripController(PowerStripRepository psrepository) {
    this.psrepository = psrepository;
  }

  @GetMapping("/powerstrip/register")
  public String ps_register() {
    return "PowerStrip/register";
  }

  @PostMapping("/powerstrip/register")
  public String ps_register(@ModelAttribute PowerStrip powerstrip, Model model) {
    ps_service.register(powerstrip);
    return "redirect:/device";
  }

  @GetMapping("/powerstrip/edit/{id}")
  public String ps_edit(@PathVariable("id") long ps_id, Model model) {
    // idと一致するデータを取得
    model.addAttribute("powerstrip", psrepository.findById(ps_id));
    return "PowerStrip/edit";
  }

  @PostMapping("/powerstrip/update/{id}")
  public String ps_update(@PathVariable("id") long ps_id, @ModelAttribute PowerStrip powerstrip, Model model) {
    ps_service.update(powerstrip, ps_id);
    return "redirect:/device";
  }

  @PostMapping("/powerstrip/delete/{id}")
  public String ps_delete(@PathVariable("id") long ps_id, Model model) {
    ps_service.delete(ps_id);
    return "redirect:/device";
  }

  // 初期データ
  @PostConstruct
  public void dataInit() {
    PowerStrip Panasonic = new PowerStrip();
    Panasonic.setPs_maker_name("Panasonic");
    Panasonic.setPs_name("電源タップ");
    Panasonic.setPs_code("P01");
    Panasonic.setOutllet_number(3);
    Panasonic.setWatt(1200);
    psrepository.saveAndFlush(Panasonic);

    PowerStrip Apw = new PowerStrip();
    Apw.setPs_maker_name("Apw");
    Apw.setPs_name("電源タップ");
    Apw.setPs_code("APW01");
    Apw.setOutllet_number(4);
    Apw.setWatt(1500);
    psrepository.saveAndFlush(Apw);
  }

}
