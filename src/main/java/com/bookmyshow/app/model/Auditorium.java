package com.bookmyshow.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private String auditoriumId;

    @Column(name = "cinemaId")
    private String cinemaId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "auditoriumId")

    private List<AuditoriumRow> auditoriumRows;
}