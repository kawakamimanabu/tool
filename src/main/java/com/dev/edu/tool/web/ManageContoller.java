package com.dev.edu.tool.web;

import java.util.Date;
import java.util.List;

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
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.CommentForm;
import com.dev.edu.tool.service.LoginStaffDetails;

@Controller
@RequestMapping("manage")
public class ManageContoller extends BaseController {
  
  @GetMapping
  public String showList(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    super.setModel(model);
    return "manage/list.html";
  }
  
  @PostMapping(path = "add", params = "goToHistory")
  public String goToHistory(Model model, @RequestParam Integer reportId) {
    Report report = reportService.findOne(reportId);
    Staff staff = staffService.findOne(report.getStaff().getStaffId());
    List<ReportHistory> reports = reportHistoryService.findHistory(staff);
    model.addAttribute("staff", staff);
    model.addAttribute("reports", reports);
    return "manage/history.html";
  }
  
  @PostMapping(path = "history", params = "goToList")
  public String goToList(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    super.setModel(model);
    return "manage/list.html";
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
    Staff staff = staffService.findOne(report.getStaff().getStaffId());
    List<Comment> comments = commentService.findAllByReport(report);
    model.addAttribute("staff", staff);
    model.addAttribute("report", report);
    model.addAttribute("comments", comments);
    model.addAttribute("commentForm", new CommentForm());
    return "manage/detail.html";
  }

  @PostMapping(path = "add")
  public String addComment(Model model, @RequestParam Integer reportId, @Validated CommentForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return showDetail(model, reportId);
    }
    Report report = reportService.findOne(reportId);
    Comment comment = new Comment();
    comment.setReport(report);
    comment.setStaff(staffDetails.getStaff());
    comment.setCommentedWhen(new Date());
    comment.setComment(form.getComment());
    commentService.create(comment, staffDetails.getStaff());
    Staff staff = staffService.findOne(report.getStaff().getStaffId());
    List<ReportHistory> reports = reportHistoryService.findHistory(staff);
    model.addAttribute("staff", staff);
    model.addAttribute("reports", reports);
    return "manage/history.html";
  }
  
}
