package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.edu.tool.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
  @Query("SELECT r FROM Report r WHERE r.staff.staffId=:staffId ORDER BY r.reportId DESC")
  List<Report> findAllByStaff(@Param("staffId") String staffId);
  
}
