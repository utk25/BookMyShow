package com.bookmyshow.app.dto.response;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MovieInfoEntity {

    private String cinemaName;

    private String cinemaId;

    private String auditoriumId;

    private Date startTime;
}