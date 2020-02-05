/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
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