package com.example.amperewattcalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.amperewattcalculation.models.PowerStrip;

public interface PowerStripRepository extends JpaRepository<PowerStrip, Long> {

}
