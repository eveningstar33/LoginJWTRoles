package com.dgs.springbootjwtauth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgs.springbootjwtauth.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
