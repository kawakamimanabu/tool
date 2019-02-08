package com.dev.edu.tool.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.edu.tool.domain.Notification;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.domain.Subscriber;
import com.dev.edu.tool.form.NotificationForm;
import com.dev.edu.tool.service.LoginStaffDetails;
import com.dev.edu.tool.service.NotificationService;
import com.dev.edu.tool.service.StaffService;
import com.dev.edu.tool.service.SubscriberService;

@Controller
@RequestMapping("notification")
public class NotificationController extends BaseController {
  
  @PostMapping
  public String showAddNotification(Model model, @Validated NotificationForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    model.addAttribute("notificationForm", new NotificationForm());
    return "manage/notification.html";
  }
  
  @PostMapping(path="detail")
  public String showNotificationDetail(Model model, @RequestParam Integer notificationId) {
    this.setNotification(notificationId, model);
    this.setSubscriber(notificationId, model);
    return "manage/notificationdetail.html";
  }

  @PostMapping(path = "detail", params = "goToList")
  public String goToList(Model model, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    return "forward:showList";
  }
  
  @PostMapping(path="add")
  public String addNotification(Model model, @Validated NotificationForm form, BindingResult result, @AuthenticationPrincipal LoginStaffDetails staffDetails) {
    if (result.hasErrors()) {
      return showAddNotification(model, form, result, staffDetails);
    }
    Notification noti = new Notification();
    noti.setTitle(form.getTitle());
    noti.setNotification(form.getNotification());
    noti.setCreatedWhen(new Date());
    Notification notification = notificationService.create(noti, staffDetails.getStaff());
    List<Staff> newStaffList = staffService.findNewStaff();
    for (Staff staff : newStaffList) {
      Subscriber sub = new Subscriber();
      subscriberService.create(sub, notification, staff);
    }
    super.setModel(model);
    return "manage/list.html";
  }

  private void setNotification(Integer notificationId, Model model) {
    Notification notification = notificationService.findOne(notificationId);
    model.addAttribute("title", notification.getTitle());
    model.addAttribute("notification", notification.getNotification());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    model.addAttribute("createdWhen", sdf.format(notification.getCreatedWhen()));
    model.addAttribute("staff", notification.getStaff());
  }
  
  private void setSubscriber(Integer notificationId, Model model) {
    List<Subscriber> subList = subscriberService.findByNotificationId(notificationId);
    model.addAttribute("subscribers", subList);
  }
  
}
