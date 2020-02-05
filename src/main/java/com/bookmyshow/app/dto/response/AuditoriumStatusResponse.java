package com.bookmyshow.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AuditoriumStatusResponse {

    private List<AuditoriumStatus> auditoriumStatuses;
}