package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.Auditorium;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuditoriumRepository extends CrudRepository<Auditorium, String> {

    @Query("select a from Auditorium a " +
            "where a.auditoriumId in (:auditorium_Ids)")
    List<Auditorium> findByAuditoriumId(@Param("auditorium_Ids") List<String> auditoriumIds);
}