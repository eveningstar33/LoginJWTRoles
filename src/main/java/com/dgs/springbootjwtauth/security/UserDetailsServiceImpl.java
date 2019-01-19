package com.dgs.springbootjwtauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dgs.springbootjwtauth.entity.ApplicationUser;
import com.dgs.springbootjwtauth.entity.Role;
import com.dgs.springbootjwtauth.repos.ApplicationUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
				
		List<GrantedAuthority> grantedList = getGrantedList(username);
				
		UserDetails userDetails = new User(applicationUser.getUsername(), applicationUser.getPassword(), grantedList);

		return userDetails;
	}
	
	public List<GrantedAuthority> getGrantedList(String username) {
		List<Role> roleNames = applicationUserRepository.findByUsername(username).getAuthorities();

		List<GrantedAuthority> grantedList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (Role role: roleNames) {
				String roleStr = role.getAuthority();
				GrantedAuthority authority = new SimpleGrantedAuthority(roleStr);
				grantedList.add(authority);
			}
		}
		return grantedList;
	}

}

