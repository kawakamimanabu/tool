package com.dev.edu.tool.web;

import java.util.Date;
import java.util.List;

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
    super.setNotification(notificationId, model);
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

  
  private void setSubscriber(Integer notificationId, Model model) {
    List<Subscriber> subList = subscriberService.findByNotificationId(notificationId);
    model.addAttribute("subscribers", subList);
  }
  
}
