package com.example.amperewattcalculation.controllers;

import javax.annotation.PostConstruct;

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

@Controller
public class CalculationController {

  // PowerStripクラスのフィールドをfinalにする
  private final PowerStripRepository psrepository;
  private final ItemRepository itemrepository;

  public CalculationController(PowerStripRepository psrepository, ItemRepository itemrepository) {
    this.psrepository = psrepository;
    this.itemrepository = itemrepository;
  }

  @GetMapping("/")
  public String calculation() {
    return "calculation";
  }

  @GetMapping("/device")
  public String device(@ModelAttribute PowerStrip powerstrip, Model model) {
    model.addAttribute("powerstrips", psrepository.findAll());
    model.addAttribute("items", itemrepository.findAll());
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
    model.addAttribute("powerstrip", psrepository.findById(ps_id));
    return "PowerStrip/edit";
  }

  @PostMapping("/powerstrip/update/{id}")
  public String ps_update(@PathVariable("id") long ps_id, Model model) {
    // データの更新処理を追記、メソッド内の引数は何が必要かわかってない
    return "calculation";
  }

  @PostMapping("/powerstrip/delete/{id}")
  public String ps_delete(@PathVariable("id") long ps_id, Model model) {
    // データの削除処理を追記、メソッド内の引数は何が必要かわかってない
    return "calculation";
  }

  @GetMapping("/item/register")
  public String item_register() {
    return "Item/register";
  }

  @PostMapping("/item/register")
  public String item_register(@ModelAttribute Item item) {
    return "device";
  }

  @GetMapping("/item/edit/{id}")
  public String item_edit(@PathVariable("id") long item_id, Model model) {
    // idと一致するデータを取得
    model.addAttribute("item", itemrepository.findById(item_id));
    return "Item/edit";
  }

  @PostMapping("/item/update/{id}")
  public String item_update(@PathVariable("id") long item_id, Model model) {
    // データの更新処理を追記、メソッド内の引数は何が必要かわかってない
    return "calculation";
  }

  @PostMapping("/item/delete/{id}")
  public String item_delete(@PathVariable("id") long item_id, Model model) {
    // データの削除処理を追記、メソッド内の引数は何が必要かわかってない
    return "calculation";
  }

  // 初期データ
  @PostConstruct
  public void dataInit() {
    PowerStrip Panasonic = new PowerStrip();
    Panasonic.setPs_maker_name("Panasonic");
    Panasonic.setPs_name("電源タップ");
    Panasonic.setPs_code("P01");
    Panasonic.setOutllet_number(3);
    psrepository.saveAndFlush(Panasonic);

    PowerStrip Apw = new PowerStrip();
    Apw.setPs_maker_name("Apw");
    Apw.setPs_name("電源タップ");
    Apw.setPs_code("APW01");
    Apw.setOutllet_number(3);
    psrepository.saveAndFlush(Apw);

    Item iPhone = new Item();
    iPhone.setItem_maker_name("Apple");
    iPhone.setItem_name("iPhone13");
    iPhone.setItem_code("AI13");
    iPhone.setAmpere(20);
    iPhone.setWatt(10);
    itemrepository.saveAndFlush(iPhone);
  }
}
