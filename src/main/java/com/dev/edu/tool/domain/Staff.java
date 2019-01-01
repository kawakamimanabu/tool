package com.dev.edu.tool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs")
public class Staff {
  @Id
  @Column(name = "staff_id", unique = true, nullable = false)
  private String staffId;
  @JsonIgnore
  private String name;
  @JsonIgnore
  private String encodedPassword;
  @JsonIgnore
  private int role;
}
