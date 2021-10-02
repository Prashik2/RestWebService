package com.springboot.restwebservice.restwebservice.user;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
//import javax.validation.constraints.Size;

//import org.hibernate.annotations.Generated;
//import org.springframework.data.annotation.*;
import org.springframework.stereotype.Component;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2,message="Minimum size of username must be 2")
	private String name;
	
	@Past
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	protected User() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public User(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	
	public User(String name, Date dob) {
		super();
		this.name = name;
		this.dob = dob;
	}
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	
}
