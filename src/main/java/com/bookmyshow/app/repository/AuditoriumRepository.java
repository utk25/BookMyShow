package com.bookmyshow.app.repository;


import com.bookmyshow.app.model.Auditorium;
import org.springframework.data.repository.CrudRepository;

public interface AuditoriumRepository extends CrudRepository<Auditorium, String> {
}