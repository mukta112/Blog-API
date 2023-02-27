package com.masai.blogApp.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer blogId;
	@Enumerated(EnumType.STRING)	//VALUES: [Technology, Art, Sports]
	private Category category;	
	private String content;
	private LocalDateTime timeStamp;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
	private List<Comment> comments=new ArrayList<>();
}
