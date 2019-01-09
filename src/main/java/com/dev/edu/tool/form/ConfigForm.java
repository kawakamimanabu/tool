package com.dev.edu.tool.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dev.edu.tool.constraint.FieldMatch;

import lombok.Data;

@Data
@FieldMatch(first = "newPassword", second = "newPasswordConfirm", message = "パスワードが一致しません。")
public class ConfigForm {
  @NotNull
  private String staffId;
  @NotBlank(message="現在のパスワードが入力されていません")
  private String currentPassword;
  @Size(min = 8)
  @NotBlank(message="新しいパスワードが入力されていません")
  private String newPassword;
  @Size(min = 8)
  @NotBlank(message="新しいパスワード(確認)が入力されていません")
  private String newPasswordConfirm;
}
