package com.example.amperewattcalculation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.amperewattcalculation.models.PowerStrip;
import com.example.amperewattcalculation.repository.PowerStripRepository;

@Service
public class PowerStripService {

  @Autowired
  PowerStripRepository psrepository;

  //登録処理
  public void register(@ModelAttribute PowerStrip powerstrip){
    psrepository.saveAndFlush(powerstrip);
  }

  // 更新処理
  public void update(@ModelAttribute PowerStrip powerstrip, @PathVariable("id") long id) {

    PowerStrip editps = psrepository.findById(id).orElseThrow();
    editps.setPs_maker_name(powerstrip.getPs_maker_name());
    editps.setPs_name(powerstrip.getPs_name());
    editps.setPs_code(powerstrip.getPs_code());
    editps.setOutllet_number(powerstrip.getOutllet_number());
 
    psrepository.saveAndFlush(editps);

  }

  //削除処理
  public void delete(@PathVariable long id) {
    psrepository.deleteById(id);
  }

}
