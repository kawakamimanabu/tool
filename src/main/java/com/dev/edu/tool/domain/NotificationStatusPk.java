package com.dev.edu.tool.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class NotificationStatusPk implements Serializable {
  private static final long serialVersionUID = 4785600763694512784L;

  private Integer notificationId;
  private String checkedBy;
}
