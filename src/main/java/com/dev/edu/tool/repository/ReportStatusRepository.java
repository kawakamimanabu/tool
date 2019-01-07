package com.dev.edu.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.edu.tool.domain.ReportStatus;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, String> {

}
