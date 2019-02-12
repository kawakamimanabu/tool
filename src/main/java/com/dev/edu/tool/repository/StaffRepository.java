package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.edu.tool.domain.Staff;


public interface StaffRepository extends JpaRepository<Staff, String> {
  @Query("SELECT s FROM Staff s WHERE s.role=0 ORDER BY s.staffId ASC")
  List<Staff> findNewStaff();

}
