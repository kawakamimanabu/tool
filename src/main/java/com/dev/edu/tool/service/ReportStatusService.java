package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.ReportStatus;
import com.dev.edu.tool.repository.ReportStatusRepository;

@Service
@Transactional
public class ReportStatusService {
  @Autowired
  private ReportStatusRepository reportStatusRepository;

  public List<ReportStatus> findAll() {
    return reportStatusRepository.findAll();
  }

}
