package com.dgs.springbootjwtauth.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "appuser")
public class ApplicationUser {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private long id;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, 
		CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "appuser_role",
			joinColumns = @JoinColumn(name = "appuser_id"),
			inverseJoinColumns = @JoinColumn(name = "approle_id"))
	private List<Role> authorities;
	
	public ApplicationUser() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	
}
