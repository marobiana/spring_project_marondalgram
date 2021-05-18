package com.marobiana.test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@Autowired
	private TestDAO testDAO;
	
	@RequestMapping("/test1")
	@ResponseBody
	public String helloWorld() {
		return "Hello world!";
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public List<Map<String, Object>> getLike() {
		return testDAO.selectLike();
	}
	
	@RequestMapping("/test3")
	public String goToJsp() {
		return "test/test";
	}
}
