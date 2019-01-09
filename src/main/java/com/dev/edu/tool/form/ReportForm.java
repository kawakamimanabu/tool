package com.dev.edu.tool.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReportForm {
  @NotBlank(message="レポート内容を記載してください")
  private String content;
}
