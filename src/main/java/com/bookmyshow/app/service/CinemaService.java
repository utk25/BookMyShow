package com.bookmyshow.app.service;

import com.bookmyshow.app.enums.AuditoriumRowPos;
import com.bookmyshow.app.model.Auditorium;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.request.AudiLayoutRequest;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import com.bookmyshow.app.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private AuditoriumRowRepository auditoriumRowRepository;

    public String addCinemaLayout(AudiLayoutRequest audiLayoutRequest) {
        Auditorium auditorium = new Auditorium();
        auditorium.setCinemaId(audiLayoutRequest.getCinemaId());
        // generate method for auditorium id
        String audiId = generateAuditoriumId();
        auditorium.setAuditoriumId(audiId);
        auditoriumRepository.save(auditorium);
        List<Map<String, Integer>> noOfRows = audiLayoutRequest.getNoOfRows();
        for (Map<String, Integer> row : noOfRows) {
            for (Map.Entry<String, Integer> entry : row.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                AuditoriumRow auditoriumRow = new AuditoriumRow();
                // generate method for auditorium id
                auditoriumRow.setAuditoriumId(audiId);
                auditoriumRow.setAuditoriumRowId(generateAuditoriumRowId());
                auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.getByPos(key));
                auditoriumRow.setMaxCapacity(value);
                auditoriumRow.setBookingStatus(0);
                auditoriumRowRepository.save(auditoriumRow);
            }
        }
        return audiId;
    }

    private String generateAuditoriumId() {
        return randomId();
    }

    private String generateAuditoriumRowId() {
        return randomId();
    }

    private String randomId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}