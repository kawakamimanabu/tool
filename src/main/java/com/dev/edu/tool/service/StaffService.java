package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public Staff findOne(String staffId) {
    return staffRepository.getOne(staffId);
  }

  /**
   * Staff を DB に新規追加します。
   * 
   * @param staff
   * @return
   */
  public Staff create(Staff staff) {
    return staffRepository.save(staff);
  }

  public Staff update(Staff staff) {
    return staffRepository.saveAndFlush(staff);
  }
  
  /**
   * Staff を DB から削除します。
   * 
   * @param staff
   */
  public void delete(Staff staff) {
    staffRepository.delete(staff);
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
        "$2a$08$ggO8JApds/zH4Lju/PBfDuJLIBlp8eZoiwUXzW.2S1IJ0.sUvPDBa");
    staff.setRole(0);
    create(staff);
    return staff;
  }

}
