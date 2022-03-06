package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
	@GetMapping("/message")
	public String message(Model model) {
		// 傳送給 Thymeleaf 的資料
		List<Integer> scores = Arrays.asList(80, 47, 90, 100, 55);
		model.addAttribute("name", "John");
		model.addAttribute("age", "<H1>18</H1>");
		model.addAttribute("scores", scores);
		return "message";
	}
}
