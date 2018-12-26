package com.dev.edu.tool.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STAFFS")
@ToString(exclude = "reports")
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
  
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "report")
  private List<Report> reports;
}
