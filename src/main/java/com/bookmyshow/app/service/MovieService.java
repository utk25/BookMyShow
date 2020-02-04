/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.service;

import com.bookmyshow.app.convertor.AuditoriumConvertor;
import com.bookmyshow.app.convertor.MovieConvertor;
import com.bookmyshow.app.exception.OverlapTimeException;
import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.request.AddMovieRequest;
import com.bookmyshow.app.model.Movie;
import com.bookmyshow.app.repository.AuditoriumMovieRepository;
import com.bookmyshow.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieConvertor movieConvertor;

    @Autowired
    private AuditoriumConvertor auditoriumConvertor;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuditoriumMovieRepository auditoriumMovieRepository;

    public AuditoriumMovieMapping addMovie(AddMovieRequest addMovieRequest) {
        Optional<Movie> movieDB = movieRepository.findById(addMovieRequest.getMovieId());

        Movie movie = movieDB.orElseGet(() -> movieRepository.save(movieConvertor.createMovie(addMovieRequest)));

        AuditoriumMovieMapping auditoriumMovieMapping = auditoriumConvertor
                .createAuditoriumMovieMapping(addMovieRequest, movie);

        List<AuditoriumMovieMapping> list = auditoriumMovieRepository
                .findMovieByTimeRange(addMovieRequest.getStartTime(),
                addMovieRequest.getEndTime(), addMovieRequest.getAudiId());

        if (list.isEmpty()) {
            auditoriumMovieMapping = auditoriumMovieRepository.save(auditoriumMovieMapping);
        } else {
            throw new OverlapTimeException("Movie already exists for this cinema between this duration");
        }

        auditoriumMovieRepository.findMovieInAllCinema(movie.getMovieId(), addMovieRequest.getCity());
        return auditoriumMovieMapping;
    }
}