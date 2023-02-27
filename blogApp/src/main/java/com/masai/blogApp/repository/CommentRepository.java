package com.masai.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.blogApp.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
