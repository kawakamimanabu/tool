package com.dev.edu.tool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer notificationId;
  
  @Column(nullable = false)
  private String title;
  
  @Column(nullable = false, columnDefinition = "mediumtext")
  private String notification;
  
  @Column(nullable = false)
  private Date createdWhen;

  @Column(nullable = false)
  private Integer status;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "staffId")
  private Staff staff;
  
}
