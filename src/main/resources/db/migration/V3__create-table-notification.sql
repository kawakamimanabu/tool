
CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(512) NOT NULL,
  `notification` mediumtext NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  `created_when` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `staff_id` varchar(60) NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `FK_NOTIFICATIONS_STAFFID` (`staff_id`),
  CONSTRAINT `FK_NOTIFICATIONS_STAFFID` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `subscribers` (
  `subscriber_id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_id` int(11) NOT NULL,
  `staff_id` varchar(60) NOT NULL,
  `checked_when` timestamp NULL,
  PRIMARY KEY (`subscriber_id`),
  KEY `FK_SUBSCRIBERS_NOTIFICATIONID` (`notification_id`),
  KEY `FK_SUBSCRIBERS_STAFFID` (`staff_id`),
  CONSTRAINT `FK_SUBSCRIBERS_NOTIFICATIONID` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`notification_id`) ON DELETE CASCADE,
  CONSTRAINT `FK_SUBSCRIBERS_STAFFID` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE VIEW notification_status AS
select 
noti.notification_id
, noti.title
, noti.notification
, noti.created_when
, noti.staff_id created_by
, sub.staff_id checked_by
, sub.checked_when
from 
 education.notifications noti
left join subscribers sub on noti.notification_id = sub.notification_id
