package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.edu.tool.domain.ReportHistory;

public interface ReportHistoryRepository extends JpaRepository<ReportHistory, Integer> {
  @Query("SELECT h FROM ReportHistory h WHERE h.staffId=:staffId ORDER BY h.reportId DESC")
  List<ReportHistory> findHistoryByStaff(@Param("staffId") String staffId);

}
