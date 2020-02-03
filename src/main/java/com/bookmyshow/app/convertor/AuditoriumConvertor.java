/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.convertor;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.model.Movie;
import com.bookmyshow.app.model.MovieRequest;
import org.springframework.stereotype.Component;

@Component
public class AuditoriumConvertor {

    public AuditoriumMovieMapping createAuditoriumMovieMapping(MovieRequest movieRequest, Movie movie) {
        AuditoriumMovieMapping auditoriumMovieMapping = new AuditoriumMovieMapping();
        auditoriumMovieMapping.setAudiId(movieRequest.getAudiId());
        auditoriumMovieMapping.setMovieId(movie.getMovieId());
        auditoriumMovieMapping.setCity(movieRequest.getCity());
        auditoriumMovieMapping.setStartTime(movieRequest.getStartTime());
        auditoriumMovieMapping.setEndTime(movieRequest.getEndTime());
        auditoriumMovieMapping.setMovieId(movieRequest.getMovieId());
        return auditoriumMovieMapping;
    }
}