package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	// 加入 sample data
	@GetMapping("/add/sample/data")
	@ResponseBody
	public String addSampleData() {
		Faker faker = new Faker();
		Random random = new Random();
		for(int i=0;i<100;i++) {
			User user = new User();
			user.setName(faker.name().lastName());
			user.setPassword(String.format("%04d", random.nextInt(10000)));
			user.setBirth(faker.date().birthday());
			user.setHeight(random.nextInt(50) + 151);
			user.setWeight(random.nextInt(20) + (int)((user.getHeight()-80) * 0.7));
			userRepository.save(user);
		}
		return "Add sample data OK!";
	}
	// 列出 sample data
	@GetMapping("/list/sample/data")
	@ResponseBody
	public List<User> listSampleData() {
		return userRepository.findAll();
	}
	
}
