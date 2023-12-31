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
@Table(name = "power_strips")
public class PowerStrip {

  @Id
  @GeneratedValue
  private Long ps_id;

  private Long user_id;

  @Size(max = 50)
  private String ps_maker_name;

  @NotBlank
  @Size(max = 50)
  private String ps_name;

  @Size(max = 100)
  private String ps_code;

  @NotNull
  @Min(1)
  private Integer outllet_number;

  @NotNull
  @Min(0)
  private Integer watt;

}
