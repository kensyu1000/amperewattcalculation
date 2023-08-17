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
import com.example.amperewattcalculation.repository.ItemRepository;
import com.example.amperewattcalculation.services.ItemService;

@Controller
public class ItemController {

  private final ItemRepository itemrepository;

  @Autowired
  ItemService item_service;

  public ItemController(ItemRepository itemrepository) {
    this.itemrepository = itemrepository;
  }

  @GetMapping("/item/register")
  public String item_register(Model model) {
    model.addAttribute("item", new Item());
    return "Item/register";
  }

  @PostMapping("/item/register")
  public String item_register(@ModelAttribute Item item) {
    item_service.register(item);
    return "redirect:/device";
  }

  @GetMapping("/item/edit/{id}")
  public String item_edit(@PathVariable("id") long item_id, Model model) {
    // idと一致するデータを取得
    model.addAttribute("item", itemrepository.findById(item_id));
    return "Item/edit";
  }

  @PostMapping("/item/update/{id}")
  public String item_update(@PathVariable("id") long item_id, @ModelAttribute Item item, Model model) {
    item_service.update(item, item_id);

    return "redirect:/device";
  }

  @PostMapping("/item/delete/{id}")
  public String item_delete(@PathVariable("id") long item_id, Model model) {
    item_service.delete(item_id);
    return "redirect:/device";
  }

  // 初期データ
  @PostConstruct
  public void dataInit() {

    Item Wserver = new Item();
    Wserver.setItem_maker_name("アクアクララ");
    Wserver.setItem_name("ウォーターサーバー");
    Wserver.setItem_code("AW00");
    Wserver.setAmpere(3.0);
    Wserver.setWatt(300);
    itemrepository.saveAndFlush(Wserver);

    Item cleaner = new Item();
    cleaner.setItem_maker_name("DAISON");
    cleaner.setItem_name("掃除機");
    cleaner.setItem_code("DC20");
    cleaner.setAmpere(8.0);
    cleaner.setWatt(800);
    itemrepository.saveAndFlush(cleaner);

    Item oven = new Item();
    oven.setItem_maker_name("日立");
    oven.setItem_name("電子レンジ");
    oven.setItem_code("O01");
    oven.setAmpere(10.0);
    oven.setWatt(1000);
    itemrepository.saveAndFlush(oven);

    Item hairdryer = new Item();
    hairdryer.setItem_maker_name("Panasonic");
    hairdryer.setItem_name("ドライヤー");
    hairdryer.setItem_code("HD01");
    hairdryer.setAmpere(9.0);
    hairdryer.setWatt(900);
    itemrepository.saveAndFlush(hairdryer);
  }

}
