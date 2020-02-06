package com.bookmyshow.app.convertor;


import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.model.AuditoriumRowStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditoriumRowStatusConvertor {

    public AuditoriumRowStatus createAuditoriumRowStatus(AuditoriumRow auditoriumRow, Date startTime) {
        AuditoriumRowStatus auditoriumRowStatus = new AuditoriumRowStatus();
        auditoriumRowStatus.setAuditoriumRowId(auditoriumRow.getAuditoriumRowId());
        auditoriumRowStatus.setStartTime(startTime);
        auditoriumRowStatus.setStatus(0);
        auditoriumRowStatus.setAuditoriumRowPos(auditoriumRow.getAuditoriumRowPos());
        return auditoriumRowStatus;
    }
}