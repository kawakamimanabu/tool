package com.dev.edu.tool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.repository.StaffRepository;


@Service
public class LoginStaffDetailsService implements UserDetailsService {
  @Autowired
  StaffRepository staffRepository;
  
  @Override
  public UserDetails loadUserByUsername(String staffId) throws UsernameNotFoundException {
    Optional<Staff> optionalStaff = staffRepository.findById(staffId);
    if (!optionalStaff.isPresent()) {
      throw new UsernameNotFoundException("The requested tanto is not found.");
    }
    return new LoginStaffDetails(optionalStaff.get());
  }

  public Optional<Staff> findOne(String staffId) {
    return staffRepository.findById(staffId);
  }
  
}
