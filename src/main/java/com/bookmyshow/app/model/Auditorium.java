/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "auditorium_info")
public class Auditorium {

    @Id
    @Column(name = "auditoriumId")
    private Integer auditoriumId;

    @Column(name = "cinemaId")
    private Integer cinemaId;

    @OneToMany
    @JoinColumn(name = "auditoriumId")
    private List<AuditoriumRow> auditoriumRows;
}