package com.bookmyshow.app.repository;

import org.springframework.data.repository.query.Param;

public interface MovieCinemaRepository {

    public void findMovieInAllCinema(@Param("movie_Id") String movieId,
                                     @Param("city") String city);
}