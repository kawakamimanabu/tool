package com.dev.edu.tool.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentForm {
  private int reportId;
  @NotBlank(message="コメント内容を記載してください")
  private String comment;
}
