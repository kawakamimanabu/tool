package com.dev.edu.tool.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NotificationForm {
  @NotBlank(message="タイトルを記載してください")
  private String title;

  @NotBlank(message="お知らせ内容を記載してください")
  private String notification;
  
//  @NotNull
//  private String radio;
}
