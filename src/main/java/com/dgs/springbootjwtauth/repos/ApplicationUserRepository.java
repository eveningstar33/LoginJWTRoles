package com.dgs.springbootjwtauth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgs.springbootjwtauth.entity.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {

	ApplicationUser findByUsername(String username);
}
