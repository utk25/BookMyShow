package com.bookmyshow.app.controller;

import com.bookmyshow.app.dto.request.AudiLayoutRequest;
import com.bookmyshow.app.dto.response.AddCinemaLayoutResponse;
import com.bookmyshow.app.dto.response.BaseResponse;
import com.bookmyshow.app.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(method = RequestMethod.POST, value = "/audi/layout")
    public ResponseEntity<BaseResponse> addCinemaLayout(@Valid @RequestBody AudiLayoutRequest audiLayoutRequest) {
        String audiId = cinemaService.addCinemaLayout(audiLayoutRequest);
        return new ResponseEntity<>(BaseResponse.builder().data(AddCinemaLayoutResponse.builder().
                auditoriumId(audiId).build()).statusCode("0").build(),
                HttpStatus.OK);
    }

    public CinemaService getCinemaService() {
        return cinemaService;
    }

    public void setCinemaService(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }
}