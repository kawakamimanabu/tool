package com.dev.edu.tool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportStatus {
  @Id
  private Integer reportId;
  private String name;
  private Date lastReported;
  @Column(columnDefinition = "mediumtext")
  private String report;
  private Date lastCommented;
  private String lastCommentedBy;
}
