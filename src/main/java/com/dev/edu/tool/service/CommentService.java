package com.dev.edu.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.edu.tool.domain.Comment;
import com.dev.edu.tool.domain.Report;
import com.dev.edu.tool.domain.Staff;
import com.dev.edu.tool.repository.CommentRepository;

@Service
@Transactional
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;

  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  public List<Comment> findAllByReport(Report report) {
    return commentRepository.findAllByReport(report.getReportId());
  }
  
  public Comment findOne(Integer id) {
    return commentRepository.getOne(id);
  }

  public Comment create(Comment comment, Staff staff) {
    comment.setStaff(staff);
    return commentRepository.save(comment);
  }

  public Comment update(Comment comment, Staff staff) {
    comment.setStaff(staff);
    return commentRepository.save(comment);
  }

  public void delete(Integer id) {
    commentRepository.deleteById(id);
  }
}
