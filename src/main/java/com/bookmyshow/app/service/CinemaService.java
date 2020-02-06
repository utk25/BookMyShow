package com.bookmyshow.app.service;

import com.bookmyshow.app.dto.request.AudiLayoutRequest;
import com.bookmyshow.app.enums.AuditoriumRowPos;
import com.bookmyshow.app.model.Auditorium;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import com.bookmyshow.app.repository.CinemaRepository;
import com.bookmyshow.app.util.DynamicIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CinemaService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private AuditoriumRowRepository auditoriumRowRepository;

    public String addCinemaLayout(AudiLayoutRequest audiLayoutRequest) {
        Auditorium auditorium = new Auditorium();
        auditorium.setCinemaId(audiLayoutRequest.getCinemaId());

        // generate method for auditorium id
        String audiId = DynamicIdGenerator.generateDynamicId();
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
                auditoriumRow.setAuditoriumRowId(DynamicIdGenerator.generateDynamicId());
                auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.getByPos(key));
                auditoriumRow.setMaxCapacity(value);
                auditoriumRow.setBookingStatus(0);

                auditoriumRowRepository.save(auditoriumRow);
            }
        }
        return audiId;
    }

    public AuditoriumRepository getAuditoriumRepository() {
        return auditoriumRepository;
    }

    public void setAuditoriumRepository(AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
    }

    public AuditoriumRowRepository getAuditoriumRowRepository() {
        return auditoriumRowRepository;
    }

    public void setAuditoriumRowRepository(AuditoriumRowRepository auditoriumRowRepository) {
        this.auditoriumRowRepository = auditoriumRowRepository;
    }
}