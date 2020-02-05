/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.service;

import com.bookmyshow.app.convertor.AuditoriumConvertor;
import com.bookmyshow.app.convertor.MovieConvertor;
import com.bookmyshow.app.dto.request.AddMovieRequest;
import com.bookmyshow.app.dto.response.MovieInfoEntity;
import com.bookmyshow.app.dto.response.MovieInfoResponse;
import com.bookmyshow.app.exception.OverlapTimeException;
import com.bookmyshow.app.model.Auditorium;
import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.model.Cinema;
import com.bookmyshow.app.repository.AuditoriumMovieRepository;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.CinemaRepository;
import com.bookmyshow.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieConvertor movieConvertor;

    @Autowired
    private AuditoriumConvertor auditoriumConvertor;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AuditoriumMovieRepository auditoriumMovieRepository;

    public void addMovie(AddMovieRequest addMovieRequest) {

        AuditoriumMovieMapping auditoriumMovieMapping = auditoriumConvertor
                .createAuditoriumMovieMapping(addMovieRequest);

        List<AuditoriumMovieMapping> list = auditoriumMovieRepository
                .findMovieByTimeRange(addMovieRequest.getStartTime(),
                        addMovieRequest.getEndTime(), addMovieRequest.getAudiId());

        if (list.isEmpty()) {
            auditoriumMovieRepository.save(auditoriumMovieMapping);
        } else {
            throw new OverlapTimeException("Movie already exists for this cinema between this duration");
        }
    }

    public MovieInfoResponse getMovieInfo(String movieId, String cityId) {

        List<AuditoriumMovieMapping> auditoriumMovieMappings = auditoriumMovieRepository.
                findMovieByCity(cityId, movieId);
        if (!CollectionUtils.isEmpty(auditoriumMovieMappings)) {
            List<String> auditoriumIds = getAuditoriumIds(auditoriumMovieMappings);
            List<Auditorium> auditoriums = auditoriumRepository.findByAuditoriumId(auditoriumIds);
            List<String> cinemaIds = getCinemaIds(auditoriums);
            List<Cinema> cinemas = cinemaRepository.findByCinemaId(cinemaIds);

            Map<String, String> cinemaIdToName = formCinemaIdToName(cinemas);
            Map<String, String> audiIdToCinemaId = formAudiIdToCinemaId(auditoriums);
            Map<String, Date> audiIdToStartTime = formAudiIdToStartTime(auditoriumMovieMappings);
            return getMovieInfo(cinemaIdToName, audiIdToCinemaId, audiIdToStartTime, auditoriums);
        }
        return null;
    }

    private Map<String, Date> formAudiIdToStartTime(List<AuditoriumMovieMapping> auditoriumMovieMappings) {
        Map<String, Date> map = new HashMap<>();
        for (AuditoriumMovieMapping auditoriumMovieMapping : auditoriumMovieMappings) {
            map.putIfAbsent(auditoriumMovieMapping.getAudiId(), auditoriumMovieMapping.getStartTime());
        }
        return map;
    }

    private Map<String, String> formAudiIdToCinemaId(List<Auditorium> auditoriums) {
        Map<String, String> map = new HashMap<>();
        for (Auditorium auditorium : auditoriums) {
            map.putIfAbsent(auditorium.getAuditoriumId(), auditorium.getCinemaId());
        }
        return map;
    }

    private Map<String, String> formCinemaIdToName(List<Cinema> cinemas) {
        Map<String, String> map = new HashMap<>();
        for (Cinema cinema : cinemas) {
            map.putIfAbsent(cinema.getCinemaId(), cinema.getCinemaName());
        }
        return map;
    }


    private List<String> getAuditoriumIds(List<AuditoriumMovieMapping> auditoriumMovieMappings) {
        Set<String> auditoriumIds = new HashSet<>();
        for (AuditoriumMovieMapping auditoriumMovieMapping : auditoriumMovieMappings) {
            auditoriumIds.add(auditoriumMovieMapping.getAudiId());
        }
        return new ArrayList<>(auditoriumIds);
    }

    private List<String> getCinemaIds(List<Auditorium> auditoriums) {
        return auditoriums.stream().map(Auditorium::getCinemaId).collect(Collectors.toList());
    }

    private MovieInfoResponse getMovieInfo(Map<String, String> cinemaIdToName, Map<String, String> audiIdToCinemaId,
                                           Map<String, Date> audiIdToStartTime, List<Auditorium> auditoriums) {
        List<MovieInfoEntity> movieInfoEntities = new ArrayList<>();
        for (Auditorium auditorium : auditoriums) {
            String auditoriumId = auditorium.getAuditoriumId();
            String cinemaId = audiIdToCinemaId.get(auditoriumId);
            MovieInfoEntity movieInfoEntity = MovieInfoEntity.builder().auditoriumId(auditorium.getAuditoriumId())
                    .cinemaId(cinemaId)
                    .cinemaName(cinemaIdToName.get(cinemaId))
                    .startTime(audiIdToStartTime.get(auditoriumId)).build();
            movieInfoEntities.add(movieInfoEntity);
        }
        return MovieInfoResponse.builder().movieInfoEntities(movieInfoEntities).build();
    }
}