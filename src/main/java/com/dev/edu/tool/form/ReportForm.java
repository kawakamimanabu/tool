package com.dev.edu.tool.form;

import javax.validation.constraints.NotNull;

import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.Staff;

import lombok.Data;

@Data
public class ReportForm {
  @NotNull
  private String content;
}
