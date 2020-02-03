/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "movie_info")
public class Movie {

    @Id
    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "movieName")
    private String movieName;

    @OneToMany
    @JoinColumn(name = "cinemaId")
    private List<Cinema> cinemas;
}