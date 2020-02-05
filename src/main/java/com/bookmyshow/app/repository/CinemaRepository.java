package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.Cinema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema, String> {

    @Query("select c from Cinema c " +
            "where c.cinemaId in (:cinema_Ids)")
    List<Cinema> findByCinemaId(@Param("cinema_Ids") List<String> cinemaIds);
}