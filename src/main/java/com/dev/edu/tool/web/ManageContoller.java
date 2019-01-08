package com.dev.edu.tool.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.edu.tool.domain.Comment;
import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.ReportHistory;
import com.dev.edu.tool.domain.ReportStatus;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.CommentForm;
import com.dev.edu.tool.service.CommentService;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.ReportHistoryService;
import com.dev.edu.tool.service.ReportService;
import com.dev.edu.tool.service.ReportStatusService;
import com.dev.edu.tool.service.StaffService;

@Controller
@RequestMapping("manage")
public class ManageContoller extends BaseController {
  @Autowired
  private StaffService staffService;
  @Autowired
  private ReportHistoryService reportHistoryService;
  @Autowired
  private ReportStatusService reportStatusService;
  @Autowired
  private ReportService reportService;
  @Autowired
  private CommentService commentService;
  
  @GetMapping
  public String showList(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    List<ReportStatus> reportStatus = reportStatusService.findAll();
    model.addAttribute("staff", staffDetails.getStaff());
    model.addAttribute("reportStatus", reportStatus);
    return "manage/list.html";
  }
  
  @PostMapping(path = "history", params = "goToHistory")
  public String goToHistory(Model model, @RequestParam Integer reportId) {
    Report report = reportService.findOne(reportId);
    List<Comment> comments = commentService.findAllByReport(report);
    model.addAttribute("report", report);
    model.addAttribute("comments", comments);
    model.addAttribute("commentForm", new CommentForm());
    return "manage/detail.html";
  }
  
  @PostMapping(path = "history", params = "goToList")
  public String goToList() {
    return "redirect:/manage";
  }
  
  @PostMapping(path="history")
  public String showHistory(Model model, @RequestParam String staffId) {
    Staff staff = staffService.findOne(staffId);
    List<ReportHistory> reports = reportHistoryService.findHistory(staff);
    model.addAttribute("staff", staff);
    model.addAttribute("reports", reports);
    return "manage/history.html";
  }

  @PostMapping(path="detail")
  public String showDetail(Model model, @RequestParam Integer reportId) {
    Report report = reportService.findOne(reportId);
    List<Comment> comments = commentService.findAllByReport(report);
    model.addAttribute("report", report);
    model.addAttribute("comments", comments);
    model.addAttribute("commentForm", new CommentForm());
    return "manage/detail.html";
  }

  @PostMapping(path = "add")
  public String addComment(@RequestParam Integer reportId, @Validated CommentForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return "";
    }
    Report report = reportService.findOne(reportId);
    Comment comment = new Comment();
    comment.setReport(report);
    comment.setStaff(staffDetails.getStaff());
    comment.setCommentedWhen(new Date());
    comment.setComment(form.getComment());
    commentService.create(comment, staffDetails.getStaff());
    return "redirect:/manage";
  }
  
}
