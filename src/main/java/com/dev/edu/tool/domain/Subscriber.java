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
public class Subscriber {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer notificationId;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "notificationId")
  private Notification notification;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "staffId")
  private Staff staff;
  
  @Column(nullable = false)
  private Date checkedWhen;

}
