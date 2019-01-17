package com.dgs.springbootjwtauth.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {

	ApplicationUser findByUsername(String username);
}
