/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.model;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequest {

    private Integer cinemaId;

    private Integer audiId;

    private String city;

    private Integer movieId;

    private String movieName;

    private Date startTime;

    private Date endTime;

}