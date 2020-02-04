package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepository extends CrudRepository<Cinema, String> {
}