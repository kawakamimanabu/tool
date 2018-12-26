package com.dev.edu.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.edu.tool.domain.Staff;


public interface StaffRepository extends JpaRepository<Staff, String> {
}
