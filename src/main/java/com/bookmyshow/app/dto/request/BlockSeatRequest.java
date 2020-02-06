package com.bookmyshow.app.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlockSeatRequest {

    private String audiToriumId;

    private Date time;

    private List<BookingRequestInfo> booking;

}