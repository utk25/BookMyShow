package com.bookmyshow.app.controller;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.request.AddMovieRequest;
import com.bookmyshow.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/movie/info")
    public Object layout(@Valid @RequestParam String movie_id, String city){
        System.out.println(movie_id + " " + city);
        return "asasas";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addMovie")
    public AuditoriumMovieMapping addMovie(@RequestBody AddMovieRequest addMovieRequest) {
        return movieService.addMovie(addMovieRequest);
    }
}