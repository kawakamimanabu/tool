package com.dev.edu.tool.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReportForm {
  @NotNull
  private String content;
}
