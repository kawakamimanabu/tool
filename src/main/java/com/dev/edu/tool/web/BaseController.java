package com.dev.edu.tool.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.dev.edu.tool.domain.Notification;
import com.dev.edu.tool.domain.ReportStatus;
import com.dev.edu.tool.service.CommentService;
import com.dev.edu.tool.service.NotificationService;
import com.dev.edu.tool.service.ReportHistoryService;
import com.dev.edu.tool.service.ReportService;
import com.dev.edu.tool.service.ReportStatusService;
import com.dev.edu.tool.service.StaffService;
import com.dev.edu.tool.service.SubscriberService;

public abstract class BaseController {
  protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

  @Autowired
  protected StaffService staffService;
  @Autowired
  protected ReportHistoryService reportHistoryService;
  @Autowired
  protected ReportStatusService reportStatusService;
  @Autowired
  protected ReportService reportService;
  @Autowired
  protected CommentService commentService;
  @Autowired
  protected NotificationService notificationService;
  @Autowired
  protected SubscriberService subscriberService;

  /**
   * 一覧表示用のデータを取得して Model に設定します。
   * @param model
   */
  protected void setModel(Model model) {
    List<ReportStatus> reportStatus = reportStatusService.findAll();
    List<Notification> notifications = notificationService.findAll();
    model.addAttribute("reportStatus", reportStatus);
    model.addAttribute("notifications", notifications);
  }
}
