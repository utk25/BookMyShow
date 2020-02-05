package com.bookmyshow.app.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCinemaRepository {

    List<AuditoriumMovieRepository> findMovieInAllCinema(@Param("movie_Id") String movieId,
                                                         @Param("city") String city);
}