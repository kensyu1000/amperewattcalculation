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
  public String item_register() {
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

    Item iPhone = new Item();
    iPhone.setItem_maker_name("Apple");
    iPhone.setItem_name("iPhone13");
    iPhone.setItem_code("AI13");
    iPhone.setAmpere(1.0);
    iPhone.setWatt(100);
    itemrepository.saveAndFlush(iPhone);

    Item PC = new Item();
    PC.setItem_maker_name("Fujitsu");
    PC.setItem_name("ノートパソコン");
    PC.setItem_code("FL101");
    PC.setAmpere(3.0);
    PC.setWatt(300);
    itemrepository.saveAndFlush(PC);
  }

}
