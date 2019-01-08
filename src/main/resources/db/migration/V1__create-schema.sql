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

CREATE VIEW report_status AS 
SELECT
 s0.staff_id
 ,s0.name
 ,r0.report_id
 ,r0.last_reported
 ,r0.report
 ,r0.last_commented
 ,r0.last_commented_by
FROM
 staffs s0 LEFT JOIN (
SELECT
 r1.report_id
 ,r1.staff_id
 ,r1.reported_when AS last_reported
 ,r1.report
 ,c1.commented_when AS last_commented
 ,s2.name AS last_commented_by
FROM
 reports r1 
 LEFT JOIN staffs s1 ON r1.staff_id=s1.staff_id
 LEFT JOIN comments c1 LEFT JOIN staffs s2 ON c1.staff_id=s2.staff_id ON r1.report_id=c1.report_id
WHERE
 r1.reported_when = (SELECT max(reported_when) FROM reports r2 WHERE r1.staff_id=r2.staff_id)
 AND (c1.commented_when = (SELECT max(commented_when) FROM comments c2 WHERE r1.report_id=c2.report_id) OR c1.commented_when IS null) 
 ) r0 ON s0.staff_id=r0.staff_id
WHERE
role=0
ORDER BY s0.staff_id;

CREATE VIEW report_history AS
SELECT
 r1.report_id
 ,r1.staff_id
 ,r1.reported_when
 ,r1.report
 ,c1.commented_when AS last_commented
 ,s2.name AS last_commented_by
FROM
 reports r1 
 LEFT JOIN staffs s1 ON r1.staff_id=s1.staff_id
 LEFT JOIN comments c1 LEFT JOIN staffs s2 ON c1.staff_id=s2.staff_id ON r1.report_id=c1.report_id
WHERE
 c1.commented_when = (SELECT MAX(commented_when) FROM comments c2 WHERE r1.report_id=c2.report_id) OR c1.commented_when IS NULL;
