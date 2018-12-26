package com.dev.edu.tool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.Comment;
import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.repository.StaffRepository;

/**
 * 担当者情報を操作するサービスクラス
 * 
 * @author its communications, Inc.
 *
 */
@Service
@Transactional
public class StaffService {
  @Autowired
  private StaffRepository staffRepository;

  public List<Staff> findAll() {
    return staffRepository.findAll();
  }

  public Staff findOne(String tantoCd) {
    return staffRepository.getOne(tantoCd);
  }

  /**
   * Staff を DB に新規追加します。
   * 
   * @param tanto
   * @return
   */
  public Staff create(Staff tanto) {
    return staffRepository.save(tanto);
  }

  /**
   * Staff を DB から削除します。
   * 
   * @param tanto
   */
  public void delete(Staff tanto) {
    staffRepository.delete(tanto);
  }

  /**
   * 指定した mailAddress で Staff オブジェクトを生成しDBに保存します。
   * 
   * @param mailAddress
   * @return
   */
  public Staff generateTanto(String mailAddress) {
    Staff staff = new Staff();
    staff.setStaffId(mailAddress);
    staff.setEncodedPassword(
        "ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9");
    staff.setRole(0);
    staff.setReports(new ArrayList<Report>());
    staff.setComments(new ArrayList<Comment>());
    create(staff);
    return staff;
  }

}
