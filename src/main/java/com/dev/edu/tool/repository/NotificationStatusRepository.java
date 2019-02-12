package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.edu.tool.domain.NotificationStatus;
import com.dev.edu.tool.domain.NotificationStatusPk;

public interface NotificationStatusRepository extends JpaRepository<NotificationStatus, NotificationStatusPk> {
  @Query("SELECT ns FROM NotificationStatus ns WHERE ns.notificationStatusPk.checkedBy=:staffId ORDER BY ns.notificationStatusPk.notificationId DESC")
  List<NotificationStatus> findBySubscriberId(@Param("staffId") String staffId);

  @Query("SELECT ns FROM NotificationStatus ns WHERE ns.notificationStatusPk.checkedBy=:staffId AND ns.notificationStatusPk.notificationId=:notificationId")
  NotificationStatus findByNotificationIdAndSubscriberId(@Param("notificationId") Integer notificationId, @Param("staffId") String staffId);
}
