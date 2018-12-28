package com.dev.edu.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.edu.tool.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
  @Query("SELECT c FROM Comment c WHERE c.report.reportId=:reportId ORDER BY c.commentId DESC")
  List<Comment> findAllByReport(@Param("reportId") Integer reportId);
  
}
