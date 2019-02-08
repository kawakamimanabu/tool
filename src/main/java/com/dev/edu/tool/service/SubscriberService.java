package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.Notification;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.domain.Subscriber;
import com.dev.edu.tool.repository.SubscriberRepository;

@Service
@Transactional
public class SubscriberService {
  @Autowired
  private SubscriberRepository subscriberRepository;
  
  public Subscriber create(Subscriber subscriber, Notification notification, Staff staff) {
    subscriber.setStaff(staff);
    subscriber.setNotification(notification);
    return subscriberRepository.saveAndFlush(subscriber);
  }
  
  public List<Subscriber> findByNotificationId(Integer notificationId) {
    return subscriberRepository.findByNotificationId(notificationId);
  }
}
