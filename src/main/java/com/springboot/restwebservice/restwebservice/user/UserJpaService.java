package com.springboot.restwebservice.restwebservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaService {
	@Autowired
	private UserRepository userRepositoty;
	
	@Autowired
	private PostRepository postRepository;
	//users
	@GetMapping(path="jpa/users")
	public List<User> getUsers() {
		return userRepositoty.findAll();
	}
	
	//users/id
	@GetMapping(path="jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id ) {
		Optional<User> users =userRepositoty.findById(id);
		if(users.isEmpty())
			throw new UserNotFoundException("id-"+id);
		EntityModel<User> model = EntityModel.of(users.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getUsers());
		model.add(linkToUsers.withRel("get-all-users"));
		return model;
	}
	
	//Create new User return void status will be 200 ok
	@PostMapping(path="/jpa/users")
	public void createUserWithNoResponse(@Valid @RequestBody User user) {
		User savedUserS =userRepositoty.save(user);
		
		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserS.getId()).toUri();
		//return ResponseEntity.created(location).build();
	}
	
	//Create new User return ResponseEntity status will be 201 created
	@PostMapping(path="/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUserS =userRepositoty.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserS.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//users/id
		@DeleteMapping(path="jpa/users/{id}")
		public User deletetUser(@PathVariable int id ) {
			Optional<User> users = userRepositoty.findById(id);
					userRepositoty.deleteById(id);
			if(users==null)
				throw new UserNotFoundException("id-"+id);
			return users.get();
		}
		
		@GetMapping(path="jpa/users/{id}/post")
		public List<Post> getPosts(@PathVariable int id ) {
			Optional<User> users =userRepositoty.findById(id);
			if(users.isEmpty())
				throw new UserNotFoundException("id-"+id);
			return users.get().getPosts();
			//EntityModel<User> model = EntityModel.of(users.get());
			//WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getUsers());
			//model.add(linkToUsers.withRel("get-all-users"));
			//return model;
		}	
		
		@PostMapping(path="/jpa/user/{id}/post")
		public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
			Optional<User> user =userRepositoty.findById(id);
			
			if(user.isEmpty())
				throw new UserNotFoundException("id-"+id);
			User userObj = user.get();
			post.setUser(userObj);
			postRepository.save(post);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
}
