package com.bookmyshow.app.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class AddMovieRequest {

    private String cinemaId;

    private String audiId;

    private String city;

    private String movieId;

    private String movieName;

    private Date startTime;

    private Date endTime;
}