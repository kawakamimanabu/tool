CREATE TABLE IF NOT EXISTS STAFFS(staff_id VARCHAR(60) NOT NULL PRIMARY KEY, name VARCHAR(256), encoded_password VARCHAR(256) NOT NULL, role INT DEFAULT 0);
CREATE TABLE IF NOT EXISTS REPORTS(report_id INT PRIMARY KEY AUTO_INCREMENT, staff_id VARCHAR(60), reported_when TIMESTAMP NOT NULL, report VARCHAR(50000) NOT NULL);
CREATE TABLE IF NOT EXISTS COMMENTS(comment_id INT PRIMARY KEY AUTO_INCREMENT, report_id INT, staff_id VARCHAR(60) NOT NULL, commented_when TIMESTAMP NOT NULL, comment VARCHAR(50000) NOT NULL);
ALTER TABLE REPORTS ADD CONSTRAINT FK_REPORTS_STAFFID FOREIGN KEY(staff_id) REFERENCES STAFFS(staff_id) ON DELETE CASCADE;
ALTER TABLE COMMENTS ADD CONSTRAINT FK_COMMENTS_STAFFID FOREIGN KEY(staff_id) REFERENCES STAFFS(staff_id) ON DELETE CASCADE;
ALTER TABLE COMMENTS ADD CONSTRAINT FK_COMMENTS_REPORTID FOREIGN KEY(report_id)  REFERENCES REPORTS(report_id) ON DELETE CASCADE;

INSERT INTO STAFFS(staff_id, name, encoded_password) VALUES('staff-01@wing-dev.com', '渡井', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
INSERT INTO STAFFS(staff_id, name, encoded_password) VALUES('staff-02@wing-dev.com', '依田', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
INSERT INTO STAFFS(staff_id, name, encoded_password) VALUES('staff-03@wing-dev.com', '内田', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
INSERT INTO STAFFS(staff_id, name, encoded_password) VALUES('staff-04@wing-dev.com', '中村', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
INSERT INTO STAFFS(staff_id, name, encoded_password) VALUES('staff-05@wing-dev.com', '後藤', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
INSERT INTO STAFFS(staff_id, name, encoded_password) VALUES('staff-06@wing-dev.com', '岡田', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');

INSERT INTO STAFFS VALUES('kawakami@wing-dev.com', '川上', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9', 1);
