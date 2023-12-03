package com.devBlog.email.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devBlog.email.dto.EmailDto;
import com.devBlog.email.service.EmailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	private ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) {
		return emailService.sendEmail(emailDto);
	}
}
