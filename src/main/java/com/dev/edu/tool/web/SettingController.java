package com.dev.edu.tool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.ConfigForm;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.StaffService;

@Controller
@RequestMapping("setting")
public class SettingController extends BaseController {
  @Autowired
  private StaffService staffService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @PostMapping
  public String showProfile(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    model.addAttribute("staff", staff);
    model.addAttribute("configForm", new ConfigForm());
    return "setting/profile.html";
  }
  
  @PostMapping(path="reset")
  public String resetPassword(Model model, @Validated ConfigForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    if (result.hasErrors()) {
      return showProfile(model, staffDetails);
    }
    else if (!passwordEncoder.matches(form.getCurrentPassword(), staff.getEncodedPassword())) {
      result.rejectValue("currentPassword", "aaa");
      return showProfile(model, staffDetails);
    }
    staff.setEncodedPassword(passwordEncoder.encode(form.getNewPassword()));
    staffService.update(staff);
    return "redirect:/loginForm";
  }
}
