package com.dgs.springbootjwtauth.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
	
	@Autowired
	private MovieRepository movieRepository;
	
    @GetMapping("/movie")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
    
    @GetMapping("/movie/{id}") 
    public Movie getOneMovie(@PathVariable long id) {
    	return movieRepository.findById(id).get();
    }
    
    @PostMapping("/movie")
    public void addMovie(@RequestBody Movie movie) {
    	movieRepository.save(movie);
    }
    
    @PutMapping("/movie/{id}")
    public void editMovie(@PathVariable long id, @RequestBody Movie movie) {
        Movie existingMovie = movieRepository.findById(id).get();
        Assert.notNull(existingMovie, "Task not found");
        existingMovie.setName(movie.getName());
        movieRepository.save(existingMovie);
    }
    
    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable long id) {
        Movie movieToDel = movieRepository.findById(id).get();
        movieRepository.delete(movieToDel);
    }
}
