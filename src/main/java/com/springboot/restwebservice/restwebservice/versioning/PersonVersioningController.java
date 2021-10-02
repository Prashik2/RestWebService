package com.springboot.restwebservice.restwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 getName() {
		PersonV1 person = new PersonV1("Bob Charlie");
		return person;
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getName2() {
		PersonV2 person = new PersonV2(new Name("Bob","Charlie"));
		return person;
	}
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 getParamV1() {
		PersonV1 person = new PersonV1("Bob Charlie");
		return person;
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 getParamV2() {
		PersonV2 person = new PersonV2(new Name("Bob","Charlie"));
		return person;
	}
	
	@GetMapping(value="/person/header",headers="version=1")
	public PersonV1 getParamHeaderV1() {
		PersonV1 person = new PersonV1("Bob Charlie");
		return person;
	}
	
	@GetMapping(value="/person/header",headers="version=2")
	public PersonV2 getPersonHeaderV2() {
		PersonV2 person = new PersonV2(new Name("Bob","Charlie"));
		return person;
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
	public PersonV1 getProducesV1() {
		PersonV1 person = new PersonV1("Bob Charlie");
		return person;
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 getProducesV2() {
		PersonV2 person = new PersonV2(new Name("Bob","Charlie"));
		return person;
	}
}
