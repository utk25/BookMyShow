/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.convertor;

import com.bookmyshow.app.dto.request.AddMovieRequest;
import com.bookmyshow.app.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieConvertor {

    public Movie createMovie(AddMovieRequest addMovieRequest) {
        Movie movie = new Movie();
        movie.setMovieName(addMovieRequest.getMovieName());
        return movie;
    }
}