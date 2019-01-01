package com.dev.edu.tool.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentForm {
  @NotNull
  private String comment;
}
