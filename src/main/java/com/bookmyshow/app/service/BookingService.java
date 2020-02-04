package com.bookmyshow.app.service;

import com.bookmyshow.app.cache.CacheClient;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.model.AuditoriumRowStatus;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import com.bookmyshow.app.repository.BookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private AuditoriumRowRepository auditoriumRowRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private CacheClient cacheClient;

    public List<AuditoriumRow> getAuditoriumStatus(String auditoriumId, Date startTime) {

        List<AuditoriumRow> auditoriumRows = auditoriumRowRepository.findAllAuditoriumRowsByAuditoriumId(auditoriumId);

        for (AuditoriumRow auditoriumRow : auditoriumRows) {
            AuditoriumRowStatus auditoriumRowStatus = bookingStatusRepository.
                    findRowStatusByStartTime(startTime, auditoriumRow.getAuditoriumRowId());
            Integer status = auditoriumRowStatus.getStatus();
            status |= cacheClient.getAuditoriumRowSeatCount(auditoriumId);
            auditoriumRow.setBookingStatus(status);
        }
        return auditoriumRows;
    }

    public void blockAuditoriumRows() {

    }

}