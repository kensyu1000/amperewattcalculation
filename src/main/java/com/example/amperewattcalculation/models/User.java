package com.example.amperewattcalculation.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private Long user_id;

  @NotBlank
  @Size(max = 30)
  private String user_name;

  @NotBlank
  @Email
  @Size(max = 100)
  private String mailaddress;

  @NotBlank
  @Size(min = 8)
  @Size(max = 50)
  private String password;

  private Date created_at;
  private Date updated_at;
  private Date deleted_at;
}
