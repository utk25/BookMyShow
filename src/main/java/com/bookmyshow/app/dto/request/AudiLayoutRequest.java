package com.bookmyshow.app.dto.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AudiLayoutRequest {

    public String cinemaId;

    List<Map<String, Integer>> noOfRows;
}