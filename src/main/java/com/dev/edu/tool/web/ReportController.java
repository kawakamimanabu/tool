package com.dev.edu.tool.web;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.form.ReportForm;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.ReportService;


@Controller
@RequestMapping("reports")
public class ReportController {
  private static Logger logger = LoggerFactory.getLogger(ReportController.class);
  
  @Autowired
  ReportService reportService;
  
  @ModelAttribute
  ReportForm setUpForm() {
    return new ReportForm();
  }
  
  @GetMapping
  String list(Model model, @AuthenticationPrincipal LoginStaffDetails tantoDetails) {
    List<Report> reports = reportService.findAllByStaff(tantoDetails.getStaff());
    model.addAttribute("responses", reports);
    return "reports/list";
  }
  
  @PostMapping(path = "edit", params = "form")
  String editForm(@RequestParam Integer id, ReportForm form) {
    Report dummyResponse = reportService.findOne(id);
    BeanUtils.copyProperties(dummyResponse, form);
    return "responses/edit";
  }
  
  @PostMapping(path = "edit")
  String edit(@RequestParam Integer id, @Validated ReportForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return editForm(id, form);
    }
    Report report = reportService.findOne(id);
    report.setReport(form.getReport().getReport());
    report.setReportedWhen(LocalDateTime.now());
    reportService.update(report, staffDetails.getStaff());
    return "redirect:/responses";
  }
  
  @PostMapping(path = "edit", params = "goToTop")
  String goToTop() {
    return "redirect:/responses";
  }
  
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
