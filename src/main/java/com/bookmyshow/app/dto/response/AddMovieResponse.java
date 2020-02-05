package com.bookmyshow.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddMovieResponse {

    private String statusMessage;
}