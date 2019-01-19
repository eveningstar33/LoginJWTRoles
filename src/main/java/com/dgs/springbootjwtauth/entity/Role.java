package com.dgs.springbootjwtauth.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "approle")
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String authority;
	
	@JsonIgnore
//	@ManyToMany(mappedBy = "authorities")
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, 
		CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "appuser_role",
		joinColumns = @JoinColumn(name = "approle_id"),
		inverseJoinColumns = @JoinColumn(name = "appuser_id"))
	private Set<ApplicationUser> users = new HashSet<>();

	public Role() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<ApplicationUser> getUsers() {
		return users;
	}

	public void setUsers(Set<ApplicationUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return authority;
	}

}
