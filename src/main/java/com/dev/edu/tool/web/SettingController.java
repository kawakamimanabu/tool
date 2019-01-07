package com.dev.edu.tool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.StaffService;

@Controller
@RequestMapping("setting")
public class SettingController extends BaseController {
  @Autowired
  private StaffService staffService;

  @GetMapping
  public String showConfig(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    model.addAttribute("staff", staff);
    return "setting/config.html";
  }
}
