package com.masai.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.blogApp.models.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
