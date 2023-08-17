package com.example.amperewattcalculation.controllers;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
  public String ps_register(Model model) {
    model.addAttribute("powerstrip", new PowerStrip());
    return "powerStrip/register";
  }

  @PostMapping("/powerstrip/register")
  public String ps_register(@Validated @ModelAttribute PowerStrip powerstrip, BindingResult result, Model model) {
    Logger logger = LoggerFactory.getLogger(PowerStripService.class);
    if (result.hasErrors()) {
      logger.info("エラー");
      return "powerstrip/register";
    }
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

    PowerStrip SANWA = new PowerStrip();
    SANWA.setPs_maker_name("SANWA SUPPLY");
    SANWA.setPs_name("雷ガード付き");
    SANWA.setPs_code("SSG00");
    SANWA.setOutllet_number(6);
    SANWA.setWatt(1500);
    psrepository.saveAndFlush(SANWA);
  }

}
