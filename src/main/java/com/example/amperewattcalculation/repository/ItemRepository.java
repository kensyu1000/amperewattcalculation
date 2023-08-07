package com.example.amperewattcalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.amperewattcalculation.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
