package com.dev.edu.tool.form;

import java.util.List;

import com.dev.edu.tool.domain.Comment;
import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.Staff;

import lombok.Data;

@Data
public class ReportForm {
  private Staff staff;
  private Report report;
  private List<Comment> comment;
}
