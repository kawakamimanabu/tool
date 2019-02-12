package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.Notification;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {
  @Autowired
  private NotificationRepository notificationRepository;

  public Notification create(Notification notification, Staff staff) {
    notification.setStaff(staff);
    return notificationRepository.saveAndFlush(notification);
  }
  
  public List<Notification> findAll() {
    return notificationRepository.findAll();
  }
  
  public Notification findOne(Integer id) {
    return notificationRepository.getOne(id);
  }
  
  public List<Notification> findAllDesc() {
    return notificationRepository.findAllDesc();
  }
}
