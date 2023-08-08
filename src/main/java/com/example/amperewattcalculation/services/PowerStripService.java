package com.example.amperewattcalculation.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  public List<PowerStrip> findAll() {
    return psrepository.findAll();
  }

  // public void update(Long id) {

  // Logger logger = LoggerFactory.getLogger(PowerStripService.class);
  // // var abc = powerstrip.getPs_id();
  // logger.info("" + id);

  // // PowerStrip powerstrip = new PowerStrip();

  // }
  public void update(@ModelAttribute PowerStrip powerstrip, @PathVariable("id") long id) {
    Logger logger = LoggerFactory.getLogger(PowerStripService.class);
    var abc = powerstrip.getPs_maker_name();
    logger.info("" + abc);

    // Long editps_id = powerstrip.getPs_id();
    logger.info("" + id);
    PowerStrip editps = psrepository.findById(id).orElseThrow();
    editps.setPs_maker_name(powerstrip.getPs_maker_name());
    editps.setPs_name(powerstrip.getPs_name());
    editps.setPs_code(powerstrip.getPs_code());
    editps.setOutllet_number(powerstrip.getOutllet_number());

    psrepository.saveAndFlush(editps);

  }

}
