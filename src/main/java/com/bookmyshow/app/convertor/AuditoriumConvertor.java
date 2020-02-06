package com.bookmyshow.app.convertor;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.dto.request.AddMovieRequest;
import org.springframework.stereotype.Component;

@Component
public class AuditoriumConvertor {

    public AuditoriumMovieMapping createAuditoriumMovieMapping(AddMovieRequest addMovieRequest) {
        AuditoriumMovieMapping auditoriumMovieMapping = new AuditoriumMovieMapping();
        auditoriumMovieMapping.setAudiId(addMovieRequest.getAudiId());
        auditoriumMovieMapping.setCity(addMovieRequest.getCity());
        auditoriumMovieMapping.setStartTime(addMovieRequest.getStartTime());
        auditoriumMovieMapping.setEndTime(addMovieRequest.getEndTime());
        auditoriumMovieMapping.setMovieId(addMovieRequest.getMovieId());
        return auditoriumMovieMapping;
    }
}