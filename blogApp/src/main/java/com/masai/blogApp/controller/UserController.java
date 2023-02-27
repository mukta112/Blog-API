package com.masai.blogApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.blogApp.Exception.UserException;
import com.masai.blogApp.models.Blog;
import com.masai.blogApp.models.Comment;
import com.masai.blogApp.models.LoginDto;
import com.masai.blogApp.models.User;
import com.masai.blogApp.service.UserService;

@RestController
@RequestMapping("/masaiblog")
public class UserController {

	@Autowired
	private UserService userServ;
	
	@PostMapping("/user/register")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException{
		return new ResponseEntity<User>(userServ.registerUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<User> loginUserHandler(@RequestBody LoginDto login) throws UserException{
		User user = userServ.loginUser(login.getEmail(),login.getPassword());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/user/blog/{userId}")
	public ResponseEntity<Blog> publishBlogHandler(@RequestBody Blog blog,@PathVariable Integer userId) throws UserException{
		Blog b = userServ.publishBlog(blog, userId);
		return new ResponseEntity<Blog>(b, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/blogs")
	public ResponseEntity<List<Blog>> getAllBlogs(@RequestBody Blog blog) throws UserException{
		List<Blog> blogs=userServ.getAllBlogs(blog);
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@PostMapping("user/comment/{blogId}/{userId}")
	public ResponseEntity<Comment> postCommentOnOthersBlogHandler(@RequestBody Comment comment,@PathVariable Integer blogId,@PathVariable Integer userId) throws UserException{
		Comment com=userServ.postCommentOnOthersBlog(comment, blogId, userId);
		return new ResponseEntity<Comment>(com, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/blog/{blogId}/{userId}")
	public ResponseEntity<String> deleteOwnBlogHandler(@PathVariable Integer blogId,@PathVariable Integer userId) throws UserException{
		userServ.deleteOwnBlog(blogId, userId);
		return new ResponseEntity<String>("blog deleted successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping("/user/blog/{commentId}/{userId}")
	public ResponseEntity<String> deleteOwnBlogComment(Integer commentId, Integer userId) throws UserException{
		userServ.deleteOwnBlogComment(commentId, userId);
		return new ResponseEntity<String>("comment deleted successfully.", HttpStatus.OK);
	}
}
