package com.example.amperewattcalculation.services;

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

  public void update(@ModelAttribute Item item, @PathVariable("id") long id) {
    Item edititem = itemrepository.findById(id).orElseThrow();
    edititem.setItem_maker_name(item.getItem_maker_name());
    edititem.setItem_name(item.getItem_name());
    edititem.setItem_code(item.getItem_code());
    edititem.setAmpere(item.getAmpere());
    edititem.setWatt(item.getWatt());

    itemrepository.saveAndFlush(edititem);

  }

  public void delete(@PathVariable long id) {
    itemrepository.deleteById(id);
  }

}
