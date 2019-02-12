package com.dev.edu.tool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationStatus {
  @EmbeddedId
  private NotificationStatusPk notificationStatusPk;
  
  private String title;
  
  @Column(columnDefinition = "mediumtext")
  private String notification;
  
  private Date createdWhen;
  private String createdBy;

  private Date checkedWhen;
  
}
