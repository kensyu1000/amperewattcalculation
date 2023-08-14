package com.example.amperewattcalculation.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {

  @Id
  @GeneratedValue
  private Long item_id;

  // // @ManyToOne()
  // @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private Long user_id;

  @Size(max = 50)
  private String item_maker_name;

  @NotBlank
  @Size(max = 50)
  private String item_name;

  @Size(max = 100)
  private String item_code;

  @NotNull
  @Min(0)
  private Double ampere;

  @NotNull
  @Min(0)
  private Integer watt;
}
