/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.dto.request;

import lombok.Data;

@Data
public class BookingRequestInfo {

    private String auditoriumRowId;

    private String seatNumber;
}