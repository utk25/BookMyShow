/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.controller;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.model.Cinema;
import com.bookmyshow.app.model.MovieRequest;
import com.bookmyshow.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/movie/{movieId}/{city}")
    public List<Cinema> getCinemaList(@PathVariable Integer movieId) {
        //return movieService.getAllInterviews(movieId);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addMovie")
    public AuditoriumMovieMapping addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }
}