package com.bookmyshow.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "movie_info")
public class Movie {

    @Id
    @Column(name = "movieId")
    private String movieId;

    @Column(name = "movieName")
    private String movieName;
}