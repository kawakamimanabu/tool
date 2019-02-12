package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.edu.tool.domain.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
  @Query("SELECT s FROM Subscriber s WHERE s.notification.notificationId=:notificationId ORDER BY s.staff.staffId ASC")
  List<Subscriber> findByNotificationId(@Param("notificationId") Integer notificationId);

  @Query("SELECT s FROM Subscriber s WHERE s.staff.staffId=:staffId ORDER BY s.notification.notificationId DESC")
  List<Subscriber> findByStaffId(@Param("staffId") String staffId);
  
  @Query("SELECT s FROM Subscriber s WHERE s.staff.staffId=:staffId AND s.notification.notificationId=:notificationId")
  Subscriber findByNotificationIdAndStaffId(@Param("notificationId") Integer notificationId, @Param("staffId") String staffId);
}
