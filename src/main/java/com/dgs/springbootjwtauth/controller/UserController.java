package com.dgs.springbootjwtauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgs.springbootjwtauth.entity.ApplicationUser;
import com.dgs.springbootjwtauth.repos.ApplicationUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/sign-up")
	public List<ApplicationUser> findUsers() {
		return applicationUserRepository.findAll();
	}

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
