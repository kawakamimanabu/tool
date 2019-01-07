package com.dev.edu.tool.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.edu.tool.domain.ReportStatus;
import com.dev.edu.tool.service.CommentService;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.ReportService;
import com.dev.edu.tool.service.ReportStatusService;

@Controller
@RequestMapping("manage")
public class ManageContoller extends BaseController {
  @Autowired
  private ReportStatusService reportStatusService;
  @Autowired
  private ReportService reportService;
  @Autowired
  private CommentService commentService;
  
  @GetMapping
  public String list(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    List<ReportStatus> reportStatus = reportStatusService.findAll();
    model.addAttribute("reportStatus", reportStatus);
    return "manage/list.html";
  }
}
