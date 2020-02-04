package com.bookmyshow.app.model;

import com.bookmyshow.app.enums.AuditoriumRowPos;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "booking_status")
public class AuditoriumRowStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "auditoriumRowId")
    private String auditoriumRowId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "auditoriumRowPos")
    private AuditoriumRowPos auditoriumRowPos;
}