package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.NotificationStatus;
import com.dev.edu.tool.repository.NotificationStatusRepository;

@Service
@Transactional
public class NotificationStatusService {
  @Autowired
  private NotificationStatusRepository notificationStatusRepository;

  public List<NotificationStatus> findBySubscriberId(String staffId) {
    return notificationStatusRepository.findBySubscriberId(staffId);
  }
  
  public NotificationStatus findByNotificationIdAndSubscriberId(Integer notificationId, String staffId) {
    return notificationStatusRepository.findByNotificationIdAndSubscriberId(notificationId, staffId);
  }
}
