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
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.form.DetailForm;
import com.dev.edu.tool.form.ReportForm;
import com.dev.edu.tool.service.CommentService;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.ReportService;


@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
  
  @Autowired
  ReportService reportService;
  @Autowired
  CommentService commentService;
  
  @ModelAttribute
  ReportForm setUpForm() {
    return new ReportForm();
  }
  
  @GetMapping
  public String list(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    Staff staff = staffDetails.getStaff();
    List<Report> reports = reportService.findAllByStaff(staff);
    model.addAttribute("reports", reports);
    return "report/list.html";
  }
  
  @PostMapping(path = "detail")
  public String showDetail(Model model, @RequestParam Integer id) {
    Report report = reportService.findOne(id);
    List<Comment> comments = commentService.findAllByReport(report);
    model.addAttribute("report", report);
    model.addAttribute("comments", comments);
    return "report/detail.html";
  }

  @PostMapping(path = "add", params = "form")
  public String addComment( @RequestParam Integer id, @Validated DetailForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return "";
    }
    Report report = reportService.findOne(id);
    Comment comment = new Comment();
    comment.setReport(report);
    comment.setStaff(staffDetails.getStaff());
    comment.setCommentedWhen(new Date());
    reportService.create(report, staffDetails.getStaff());
    return "redirect:/report/detail";
  }
  
  @PostMapping(path = "add", params = "goToTop")
  public String goToTop() {
    return "redirect:/report";
  }
  
  @PostMapping(path = "create")
  public String create(@Validated ReportForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return "";//editForm(id, form);
    }
    Report report = new Report();
    report.setReport(form.getContent());
    report.setStaff(staffDetails.getStaff());
    report.setReportedWhen(new Date());
    reportService.create(report, staffDetails.getStaff());
    return "redirect:/report";
  }
  
//  @PostMapping(path = "detail")
//  public String edit(@RequestParam Integer id, @Validated ReportForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
//    if (result.hasErrors()) {
//      return editForm(id, form);
//    }
//    Report report = reportService.findOne(id);
//    report.setReport(form.getReport().getReport());
//    report.setReportedWhen(LocalDateTime.now());
//    reportService.update(report, staffDetails.getStaff());
//    return "redirect:/report";
//  }
  
//  @PostMapping(path = "download")
//  ResponseEntity<byte[]> download(@AuthenticationPrincipal LoginTantoDetails tantoDetails) throws IOException {
//    HttpHeaders header = new HttpHeaders();
//    header.add("Content-Type", "text/csv; charset=UTF-8");
//    String now = DateTimeFormatter.ofPattern("uuuuMMddHHmmss").format(LocalDateTime.now());
//    header.setContentDispositionFormData("filename", "apimock_" + now + ".tsv");
//    List<DummyResponse> dummyResponses = reportService.findAll();
//    Function<DummyResponse, String> convert = dummy -> {return dummy.getId() + "\t" + dummy.getResponse();};
//    //String key = DigestUtils.md5DigestAsHex((tantoDetails.getTanto().getTantocd() + "_happyApiMocking").getBytes()) + "\n";
//    String csv = dummyResponses.stream().map(convert).collect(Collectors.joining("\n"));
//    return new ResponseEntity<>(csv.getBytes("UTF-8"), header, HttpStatus.OK);
//  }
}
