/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.model.MovieInCinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface AuditoriumMovieRepository extends CrudRepository<AuditoriumMovieMapping, Integer>, MovieCinemaRepository {
    
    @Query("select distinct am " +
            "from AuditoriumMovieMapping am " +
            "where (am.startTime between :start_Time and :end_Time  " +
            "or am.endTime between :start_Time and :end_Time) " +
            " and am.audiId = :audi_Id")
    List<AuditoriumMovieMapping> findMovieByTimeRange(@Param("start_Time") Date startTime,
                                                      @Param("end_Time") Date endTime,
                                                      @Param("audi_Id") Integer audiId);
}