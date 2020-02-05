/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuditoriumStatus {

    private String auditoriumRowId;

    private Integer status;

    private Integer capacity;
}