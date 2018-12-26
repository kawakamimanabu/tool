package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.repository.ReportRepository;

@Service
@Transactional
public class ReportService {
  @Autowired
  private ReportRepository reportRepository;

  public List<Report> findAll() {
    return reportRepository.findAll();
  }

  public List<Report> findAllByStaff(Staff staff) {
    return reportRepository.findAllByStaff(staff);
  }

  
  public Report findOne(Integer id) {
    return reportRepository.getOne(id);
  }

  public Report create(Report report, Staff staff) {
    report.setStaff(staff);
    return reportRepository.save(report);
  }

  public Report update(Report report, Staff staff) {
    report.setStaff(staff);
    return reportRepository.save(report);
  }

  public void delete(Integer id) {
    reportRepository.deleteById(id);
  }
}
