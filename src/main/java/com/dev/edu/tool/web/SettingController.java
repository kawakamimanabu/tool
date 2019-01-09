package com.dev.edu.tool.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.edu.tool.domain.ReportHistory;
import com.dev.edu.tool.domain.ReportStatus;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.ConfigForm;
import com.dev.edu.tool.form.ReportForm;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.ReportHistoryService;
import com.dev.edu.tool.service.ReportStatusService;
import com.dev.edu.tool.service.StaffService;

@Controller
@RequestMapping("setting")
public class SettingController extends BaseController {
  @Autowired
  private ReportHistoryService reportHistoryService;
  @Autowired
  private ReportStatusService reportStatusService;
  @Autowired
  private StaffService staffService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @ModelAttribute
  ConfigForm setUpForm() {
    return new ConfigForm();
  }
  
  @PostMapping
  public String showProfile(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    model.addAttribute("staff", staff);
    return "setting/resetpassword.html";
  }

  @PostMapping(path = "reset", params = "goToTop")
  public String goToTop(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    if (staff.getRole() != 0) {
      List<ReportStatus> reportStatus = reportStatusService.findAll();
      model.addAttribute("reportStatus", reportStatus);
      return "manage/list.html";
    }
    List<ReportHistory> reports = reportHistoryService.findHistory(staff);
    model.addAttribute("reports", reports);
    model.addAttribute("reportForm", new ReportForm());
    return "report/list.html";
  }
  
  @PostMapping(path="reset")
  public String resetPassword(Model model, @Validated ConfigForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    if (result.hasErrors()) {
      return showProfile(model, staffDetails);
    }
    else if (!passwordEncoder.matches(form.getCurrentPassword(), staff.getEncodedPassword())) {
      result.rejectValue("currentPassword", "password.missmatch", "現在のパスワードが間違っています");
      return showProfile(model, staffDetails);
    }
    staff.setEncodedPassword(passwordEncoder.encode(form.getNewPassword()));
    staffService.update(staff);
    return "redirect:/loginForm";
  }
}
