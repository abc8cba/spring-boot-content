package com.project.demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/")
	public String loginPage() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String p = bCryptPasswordEncoder.encode("SomeCoolPassword");
		System.out.println(p);
		System.out.println(bCryptPasswordEncoder.matches("SomeCoolPasswor", p));
		return "login";
	}

}
