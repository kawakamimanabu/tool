package com.dev.edu.tool.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ConfigForm {
  @NotNull
  private String staffId;
  @NotNull
  private String name;
  @NotNull
  private String currentPassword;
  @NotNull
  private String newPassword;
  @NotNull
  private String newPasswordConfirm;
}
