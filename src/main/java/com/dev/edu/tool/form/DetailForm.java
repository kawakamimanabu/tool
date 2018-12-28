package com.dev.edu.tool.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.dev.edu.tool.domain.Comment;
import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.Staff;

import lombok.Data;

@Data
public class DetailForm {
  private Staff staff;
  private Report report;
  private List<Comment> comments;
  @NotNull
  private String comment;
}
