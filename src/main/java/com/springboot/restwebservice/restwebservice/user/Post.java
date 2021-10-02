package com.springboot.restwebservice.restwebservice.user;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	@javax.persistence.Id
	@GeneratedValue
	private Integer id;
	private String desc;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	protected Post() {
		
	}
	
	Post(String desc){
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", desc=" + desc + "]";
	}
	public void setUser(User user) {
		this.user = user;
		
	}
	
	
}
