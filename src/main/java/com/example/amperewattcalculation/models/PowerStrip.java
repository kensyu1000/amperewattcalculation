package com.example.amperewattcalculation.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PowerStrip {

  @Id
  @GeneratedValue
  private Long id;

  @Size(max = 50)
  private String ps_maker_name;

  @NotBlank
  @Size(max = 50)
  private String ps_name;

  private String ps_code;

  @NotNull
  @Min(1)
  private Integer outllet_number;

}
