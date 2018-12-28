package com.dev.edu.tool.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REPORTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reportId;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "staffId")
  private Staff staff;
  
  @Column(nullable = false)
  private Date reportedWhen;
  
  @Column(nullable = false, columnDefinition = "mediumtext")
  private String report;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "comment")
  private List<Comment> comments;
  

}
