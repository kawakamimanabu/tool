package com.dev.edu.tool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "reportId")
  private Report report;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "staffId")
  private Staff staff;
  
  @Column(nullable = false)
  private Date commentedWhen;
  
  @Column(nullable = false, columnDefinition = "mediumtext")
  private String comment;
}
