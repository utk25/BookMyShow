/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.service;

import com.bookmyshow.app.model.Auditorium;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.model.Cinema;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import com.bookmyshow.app.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private AuditoriumRowRepository auditoriumRowRepository;

    public void addCinemaLayout(Cinema cinema) {
        cinemaRepository.save(cinema);
        for (Auditorium auditorium : cinema.getAuditoriums()) {
            auditoriumRepository.save(auditorium);
            for (AuditoriumRow auditoriumRow : auditorium.getAuditoriumRows()) {
                auditoriumRowRepository.save(auditoriumRow);
            }
        }
    }
}