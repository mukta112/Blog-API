package com.masai.blogApp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String firstName;   
	private String lastName;
	private String email;
	private Integer age;
	private String gender;
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	List<Blog> blogs=new ArrayList<>();
	
}
