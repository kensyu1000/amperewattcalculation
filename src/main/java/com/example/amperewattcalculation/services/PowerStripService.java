package com.example.amperewattcalculation.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.amperewattcalculation.models.PowerStrip;
import com.example.amperewattcalculation.repository.PowerStripRepository;

@Service
public class PowerStripService {

  @Autowired
  PowerStripRepository psrepository;

  public List<PowerStrip> findAll() {
    return psrepository.findAll();
  }

  public void update(Long id) {

    Logger logger = LoggerFactory.getLogger(PowerStripService.class);
    // var abc = powerstrip.getPs_id();
    logger.info("" + id);

    // PowerStrip powerstrip = new PowerStrip();

  }

}
