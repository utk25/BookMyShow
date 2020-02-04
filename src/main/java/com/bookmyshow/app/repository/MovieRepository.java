package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {

}