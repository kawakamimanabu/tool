package com.dev.edu.tool.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.dev.edu.tool.domain.Staff;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginStaffDetails extends User {
  /** ID */
  private static final long serialVersionUID = 6017824939677533277L;
  private final Staff staff;
  
  public LoginStaffDetails(Staff staff) {
    super(
      staff.getStaffId(), 
      staff.getEncodedPassword(), 
      AuthorityUtils.createAuthorityList(staff.getRole() == 1 ? "ROLE_ADMIN" : "ROLE_USER")
      );
    this.staff = staff;
  }
}
