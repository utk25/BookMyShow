package com.bookmyshow.app.controller;

import com.bookmyshow.app.dto.request.AddMovieRequest;
import com.bookmyshow.app.dto.response.AddMovieResponse;
import com.bookmyshow.app.dto.response.BaseResponse;
import com.bookmyshow.app.dto.response.MovieInfoResponse;
import com.bookmyshow.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/info/movie/{movieId}/city/{cityId}")
    public ResponseEntity<BaseResponse> getMovieInfo(@Valid @PathVariable String movieId, @PathVariable String cityId) {
        MovieInfoResponse movieInfoResponse = movieService.getMovieInfo(movieId, cityId);
        return new ResponseEntity<>(BaseResponse.builder().data(movieInfoResponse).statusCode("0").build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addMovie")
    public ResponseEntity<BaseResponse> addMovie(@RequestBody AddMovieRequest addMovieRequest) {
        movieService.addMovie(addMovieRequest);
        return new ResponseEntity<>(BaseResponse.builder().data(AddMovieResponse.builder().
                statusMessage("Added movie successfully").build()).statusCode("0").build(),
                HttpStatus.OK);
    }
}