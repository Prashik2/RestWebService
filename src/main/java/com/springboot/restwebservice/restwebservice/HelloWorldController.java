package com.springboot.restwebservice.restwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.*;

//Controller
@RestController
public class HelloWorldController {

	@Autowired 
	private MessageSource messageSource;
	//Get
	//URI (/hello-world)
	//return string "Hello World"
	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world-bean/{name}")
	public HelloWorldBean helloWorldBeanWithParameters(@PathVariable String name) {
		return new HelloWorldBean("Hello World! " + name);
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required=false)Locale locale) {
		return messageSource.getMessage("good.morning.message", null,"Default message", 
									//locale);
									LocaleContextHolder.getLocale());
	}
	
	
}
