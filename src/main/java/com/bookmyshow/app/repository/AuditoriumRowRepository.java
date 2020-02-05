package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.AuditoriumRow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuditoriumRowRepository extends CrudRepository<AuditoriumRow, String> {

    @Query("select ar " +
            "from AuditoriumRow ar " +
            "where ar.auditoriumId = :audi_Id")
    List<AuditoriumRow> findAllAuditoriumRowsByAuditoriumId(@Param("audi_Id") String auditoriumId);
}