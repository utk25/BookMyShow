package com.bookmyshow.app.dto.request;

import lombok.Data;

@Data
public class BookingRequestInfo {

    private String auditoriumRowId;

    private String seatNumber;
}