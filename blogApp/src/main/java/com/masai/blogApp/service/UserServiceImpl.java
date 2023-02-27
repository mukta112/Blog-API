package com.masai.blogApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.blogApp.Exception.UserException;
import com.masai.blogApp.models.Blog;
import com.masai.blogApp.models.Comment;
import com.masai.blogApp.models.User;
import com.masai.blogApp.repository.BlogRepository;
import com.masai.blogApp.repository.CommentRepository;
import com.masai.blogApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BlogRepository blogRepo;
	
	@Autowired
	private CommentRepository comRepo;
	
	@Override
	public User registerUser(User user) throws UserException {
		
		if(user.getEmail()==null || user.getPassword()==null) 
			throw new UserException("User can't be registered");
		
		User regUser = userRepo.save(user);
		
		if(regUser==null)
			throw new UserException("User can't be registered");
		
		return regUser;
	}

	@Override
	public User loginUser(String email,String password) throws UserException {
		
		User user=userRepo.findByEmail(email);
		
		if(user==null)throw new UserException("Email doen't registered!");
		
		if(user.getPassword().equals(password))
			return user;
		else throw new UserException("password is incorrect!");
	}

	@Override
	public Blog publishBlog(Blog blog,Integer userId) throws UserException {
		
		Optional<User> u = userRepo.findById(userId);
		User user = new User();
		if(u.isPresent()) {
			user=u.get();
		}
		List<Blog> blogs=user.getBlogs();
		blogs.add(blog);
		blog.setUser(user);
		userRepo.save(user);
		
		return blog;
	}
	
	@Override
	public List<Blog> getAllBlogs(Blog blog) throws UserException {
		
		List<Blog> blogs=blogRepo.findAll();
		
		if(blogs==null)throw new UserException("List is empty, no blog found!");
		
		return blogs;
	}

	@Override
	public Comment postCommentOnOthersBlog(Comment comment, Integer blogId, Integer userId) throws UserException {
		
		Optional<Blog> optBlog = blogRepo.findById(blogId);
		if(optBlog.isEmpty())
			throw new UserException("Blog not found with id "+blogId);
		
		Blog presentBlog=optBlog.get();
		
		if(presentBlog.getUser().getUserId()==userId)
			throw new UserException("Can't comment on your own blog");
		
		comment.setTimeStamp(LocalDateTime.now());
		List<Comment> comments=presentBlog.getComments();
		comments.add(comment);
		
		presentBlog.setComments(comments);
		
		comRepo.save(comment);
		blogRepo.save(presentBlog);
		
		return comment;
	}

	@Override
	public String deleteOwnBlog(Integer blogId, Integer userId) throws UserException {
		
		Optional<Blog> optBlog = blogRepo.findById(blogId);
		if(optBlog.isEmpty())
			throw new UserException("Blog doesn't exist with id "+blogId);
		Blog presentBlog = optBlog.get();
		if(presentBlog.getUser().getUserId()==userId) {
			blogRepo.delete(presentBlog);
			return "blog deleted";
		}else {
			throw new UserException("You can not delete other's blog.");
		}
	}

	@Override
	public String deleteOwnBlogComment(Integer commentId, Integer userId) throws UserException {
		
		Optional<Comment> optCom = comRepo.findById(commentId);
		if(optCom.isEmpty())
			throw new UserException("Comment id doesn't exist");
		
		Comment com=optCom.get();
		
		if(com.getBlog().getUser().getUserId()==userId) {
			comRepo.delete(com);
			return "comment deleted";	
		}else {
			throw new UserException("Only owner can delete comment from his/her blog.");
		}
	}	
}
