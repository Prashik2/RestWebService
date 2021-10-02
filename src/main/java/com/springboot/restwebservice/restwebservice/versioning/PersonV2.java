package com.springboot.restwebservice.restwebservice.versioning;

public class PersonV2 {
	
	private Name name;
	
	public PersonV2(Name name) {
		this.name = name;
	}
	PersonV2(){}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	
}
