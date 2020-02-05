package com.bookmyshow.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cinema_info")
public class Cinema {

    @Id
    @Column(name = "cinemaId")
    private String cinemaId;

    @Column(name = "cinemaName")
    private String cinemaName;

    /*@JoinColumn(name = "auditoriumId")
    private List<Auditorium> auditoriums;*/

    @Column(name = "cinemaAddress")
    private String cinemaAddress;

    @Column(name = "city")
    private String city;
}