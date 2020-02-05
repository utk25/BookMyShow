package com.bookmyshow.app.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCinemaLayoutResponse {

    private String auditoriumId;
}