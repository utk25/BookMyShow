/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.model;

import com.bookmyshow.app.enums.AuditoriumRowPos;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "auditorium_row_info")
public class AuditoriumRow {

    @Id
    @Column(name = "auditoriumRowId")
    private Integer auditoriumRowId;

    @Column(name = "auditoriumRowPos")
    private AuditoriumRowPos auditoriumRowPos;

    @Column(name = "auditoriumId")
    private Integer auditoriumId;

    @Column(name = "maxCapacity")
    private Integer maxCapacity;

    @Column(name = "bookingStatus")
    private Integer bookingStatus;
}