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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.edu.tool.domain.Comment;
import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.ReportHistory;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.CommentForm;
import com.dev.edu.tool.form.ReportForm;
import com.dev.edu.tool.service.CommentService;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.ReportHistoryService;
import com.dev.edu.tool.service.ReportService;


@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
  @Autowired
  private ReportHistoryService reportHistoryService;
  @Autowired
  private ReportService reportService;
  @Autowired
  private CommentService commentService;
  
  @ModelAttribute
  ReportForm setUpForm() {
    return new ReportForm();
  }
  
  @GetMapping
  public String showList(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    List<ReportHistory> reports = reportHistoryService.findHistory(staff);
    model.addAttribute("reports", reports);
    return "report/list.html";
  }
  
  @PostMapping(path = "detail")
  public String showDetail(Model model, @RequestParam Integer reportId) {
    Report report = reportService.findOne(reportId);
    List<Comment> comments = commentService.findAllByReport(report);
    model.addAttribute("report", report);
    model.addAttribute("comments", comments);
    model.addAttribute("commentForm", new CommentForm());
    return "report/detail.html";
  }

  @PostMapping(path = "add")
  public String addComment(Model model, @RequestParam Integer reportId, @Validated CommentForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return showList(model, staffDetails);
    }
    Report report = reportService.findOne(reportId);
    Comment comment = new Comment();
    comment.setReport(report);
    comment.setStaff(staffDetails.getStaff());
    comment.setCommentedWhen(new Date());
    comment.setComment(form.getComment());
    commentService.create(comment, staffDetails.getStaff());
    return "redirect:/report";
  }
  
  @PostMapping(path = "add", params = "goToTop")
  public String goToTop() {
    return "redirect:/report";
  }
  
  @PostMapping(path = "create")
  public String createReport(Model model, @Validated ReportForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return showList(model, staffDetails);
    }
    Report report = new Report();
    report.setReport(form.getContent());
    report.setStaff(staffDetails.getStaff());
    report.setReportedWhen(new Date());
    reportService.create(report, staffDetails.getStaff());
    return showList(model, staffDetails);
  }
}
