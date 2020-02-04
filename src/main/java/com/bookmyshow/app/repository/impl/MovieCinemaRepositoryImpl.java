package com.bookmyshow.app.repository.impl;

import com.bookmyshow.app.repository.MovieCinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public class MovieCinemaRepositoryImpl implements MovieCinemaRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void findMovieInAllCinema(String movieId, String city) {
        /*Query query = entityManager.createNativeQuery("select am.movieId, am.audiId, am.city, am.startTime, " +
                "c.cinemaId, c.cinemaName, c.cinemaAddress " +
                "from auditorium_movie_info am " +
                "where am.movieId = :movieId and am.city = :city " +
                "left join cinema_info c " +
                "on am.audiId = c.audiId");

        query.setParameter("movieId", movieId);
        query.setParameter("city", city);

        List<Object> movieInCinemas = query.getResultList();
        System.out.println(movieInCinemas);*/
    }
}