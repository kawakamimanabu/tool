CREATE TABLE `staffs` (
  `staff_id` varchar(60) NOT NULL,
  `name` varchar(256) CHARACTER SET utf8 NOT NULL,
  `encoded_password` varchar(256) CHARACTER SET utf8 NOT NULL,
  `role` int(1) DEFAULT '0',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` varchar(60) CHARACTER SET utf8 NOT NULL,
  `reported_when` timestamp NOT NULL,
  `report` mediumtext CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FK_REPORTS_STAFFID` (`staff_id`),
  CONSTRAINT `FK_REPORTS_STAFFID` FOREIGN KEY (`staff_id`) REFERENCES `STAFFS` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` int(11) NOT NULL,
  `staff_id` varchar(60) CHARACTER SET utf8 NOT NULL,
  `commented_when` timestamp NOT NULL,
  `comment` mediumtext CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_COMMENTS_REPORTID` (`report_id`),
  KEY `FK_COMMENTS_STAFFID` (`staff_id`),
  CONSTRAINT `FK_COMMENTS_REPORTID` FOREIGN KEY (`report_id`) REFERENCES `REPORTS` (`report_id`) ON DELETE CASCADE,
  CONSTRAINT `FK_COMMENTS_STAFFID` FOREIGN KEY (`staff_id`) REFERENCES `STAFFS` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
