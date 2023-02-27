package com.masai.blogApp.service;

import java.util.List;

import com.masai.blogApp.Exception.UserException;
import com.masai.blogApp.models.Blog;
import com.masai.blogApp.models.Comment;
import com.masai.blogApp.models.User;

public interface UserService {
	
	public User registerUser(User user) throws UserException;
	public User loginUser(String email,String password) throws UserException;
	public Blog publishBlog(Blog blog,Integer userId)throws UserException;
	public List<Blog> getAllBlogs(Blog blog)throws UserException;
	public Comment postCommentOnOthersBlog(Comment comment,Integer blogId,Integer userId)throws UserException;
	public String deleteOwnBlog(Integer blogId,Integer userId) throws UserException;
	public String deleteOwnBlogComment(Integer commentId, Integer userId)throws UserException;
}
