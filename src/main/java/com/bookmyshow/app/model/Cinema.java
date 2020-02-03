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
@Table(name = "cinema_info")
public class Cinema {

    @Id
    @Column(name = "cinemaId")
    private Integer cinemaId;

    @Column(name = "cinemaName")
    private String cinemaName;

    @OneToMany
    @JoinColumn(name = "auditoriumId")
    private List<Auditorium> auditoriums;

    @Column(name = "cinemaAddress")
    private String cinemaAddress;

    @Column(name = "city")
    private String city;
}