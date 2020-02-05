package com.bookmyshow.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MovieInfoResponse {

    private List<MovieInfoEntity> movieInfoEntities;
}