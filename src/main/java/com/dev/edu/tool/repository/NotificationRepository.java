package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.edu.tool.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
  @Query("SELECT n FROM Notification n ORDER BY n.notificationId DESC")
  List<Notification> findAllDesc();
  
}
