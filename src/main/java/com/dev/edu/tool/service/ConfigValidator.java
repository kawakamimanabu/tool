package com.dev.edu.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.ConfigForm;

public class ConfigValidator implements Validator {

  @Autowired
  StaffService staffService;
  
  @Override
  public boolean supports(Class<?> arg0) {
    return ConfigForm.class.isAssignableFrom(arg0);
  }

  @Override
  public void validate(Object arg0, Errors errors) {
    ConfigForm configForm = (ConfigForm) arg0;
    String staffId = configForm.getStaffId();
    Staff staff = staffService.findOne(staffId);
    if (!configForm.getNewPassword().equals(configForm.getNewPasswordConfirm())) {
      errors.reject("passwords.not.match", "確認パスワードが一致しません");
    }
  }

}
