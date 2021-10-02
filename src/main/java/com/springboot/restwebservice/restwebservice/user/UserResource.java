package com.springboot.restwebservice.restwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	//users
	@GetMapping(path="/users")
	public List<User> getUsers() {
		return userDaoService.findAll();
	}
	
	//users/id
	@GetMapping(path="/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id ) {
		User users =userDaoService.findOneById(id);
		if(users==null)
			throw new UserNotFoundException("id-"+id);
		EntityModel<User> model = EntityModel.of(users);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getUsers());
		model.add(linkToUsers.withRel("get-all-users"));
		return model;
	}
	
	//Create new User return void status will be 200 ok
	@PostMapping(path="/users")
	public void createUserWithNoResponse(@Valid @RequestBody User user) {
		User savedUserS =userDaoService.save(user);
		
		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserS.getId()).toUri();
		//return ResponseEntity.created(location).build();
	}
	
	//Create new User return ResponseEntity status will be 201 created
	@PostMapping(path="/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUserS =userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserS.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//users/id
		@DeleteMapping(path="/users/{id}")
		public User deletetUser(@PathVariable int id ) {
			User users =userDaoService.deleteById(id);
			if(users==null)
				throw new UserNotFoundException("id-"+id);
			return users;
		}
}
