package com.example.amperewattcalculation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.amperewattcalculation.models.Item;
import com.example.amperewattcalculation.repository.ItemRepository;

@Service
public class ItemService {

  @Autowired
  ItemRepository itemrepository;

  // CalculationControllerにデータを渡す
  public List<Item> findAll() {
    return itemrepository.findAll();
  }

  // 登録処理
  public void register(@ModelAttribute Item item) {
    itemrepository.saveAndFlush(item);
  }

  // 更新処理
  public void update(@ModelAttribute Item item, @PathVariable("id") long id) {
    // idを item から取得しようとすると値が null になるため、idは別で取ってきている(hidden属性で保持させればよいのかも)
    Item edititem = itemrepository.findById(id).orElseThrow();
    edititem.setItem_maker_name(item.getItem_maker_name());
    edititem.setItem_name(item.getItem_name());
    edititem.setItem_code(item.getItem_code());
    edititem.setAmpere(item.getAmpere());
    edititem.setWatt(item.getWatt());

    itemrepository.saveAndFlush(edititem);

  }

  // 削除処理
  public void delete(@PathVariable long id) {
    itemrepository.deleteById(id);
  }

}
