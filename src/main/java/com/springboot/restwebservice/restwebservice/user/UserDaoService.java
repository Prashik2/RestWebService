package com.springboot.restwebservice.restwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	static int userCount=3;
	private static List<User> users = new ArrayList<>();
	
	//initialise users
	static {
		users.add(new User(1, "ABC", new Date()));
		users.add(new User(2, "BCD", new Date()));
		users.add(new User(3, "CEF", new Date()));
	}
	
	
	//findAll
	public List<User> findAll(){
		return users;
	}
	
	//save(user)
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	//findOneById 
	public User findOneById(int userId){
		for (User user : users) {
			if(user.getId()==userId)
				return user;
		}
		return null;
	}

	public User deleteById(int userId) {
		Iterator<User> userItr =users.iterator();
		while (userItr.hasNext()) {
			User user = userItr.next();
			if(user.getId()==userId) {
				userItr.remove();
				return user;
			
			}
		}
		return null;
	
	}
}
