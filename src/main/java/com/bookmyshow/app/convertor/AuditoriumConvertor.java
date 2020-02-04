/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.convertor;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.model.Movie;
import com.bookmyshow.app.request.AddMovieRequest;
import org.springframework.stereotype.Component;

@Component
public class AuditoriumConvertor {

    public AuditoriumMovieMapping createAuditoriumMovieMapping(AddMovieRequest addMovieRequest, Movie movie) {
        AuditoriumMovieMapping auditoriumMovieMapping = new AuditoriumMovieMapping();
        auditoriumMovieMapping.setAudiId(addMovieRequest.getAudiId());
        auditoriumMovieMapping.setMovieId(movie.getMovieId());
        auditoriumMovieMapping.setCity(addMovieRequest.getCity());
        auditoriumMovieMapping.setStartTime(addMovieRequest.getStartTime());
        auditoriumMovieMapping.setEndTime(addMovieRequest.getEndTime());
        auditoriumMovieMapping.setMovieId(addMovieRequest.getMovieId());
        return auditoriumMovieMapping;
    }
}