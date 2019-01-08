package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.ReportHistory;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.repository.ReportHistoryRepository;

@Service
@Transactional
public class ReportHistoryService {
  @Autowired
  private ReportHistoryRepository reportHistoryRepository;

  public List<ReportHistory> findHistory(Staff staff) {
    return reportHistoryRepository.findHistoryByStaff(staff.getStaffId());
  }

}
