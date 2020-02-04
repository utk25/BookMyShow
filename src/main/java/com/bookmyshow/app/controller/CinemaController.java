package com.bookmyshow.app.controller;

import com.bookmyshow.app.request.AudiLayoutRequest;
import com.bookmyshow.app.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object layout(@Valid @RequestBody AudiLayoutRequest audiLayoutRequest){
        return cinemaService.addCinemaLayout(audiLayoutRequest);
    }

}