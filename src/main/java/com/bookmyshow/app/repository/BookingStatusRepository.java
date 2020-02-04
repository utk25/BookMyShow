package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.AuditoriumRowStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface BookingStatusRepository extends CrudRepository<AuditoriumRowStatus, Integer> {

    @Query("select distinct ar " +
            "from AuditoriumRowStatus ar " +
            "where ar.auditoriumRowId =: auditoriumRow_Id " +
            "and ar.startTime =: start_Time")
    AuditoriumRowStatus findRowStatusByStartTime(@Param("start_Time") Date startTime,
                                                 @Param("auditoriumRow_Id") String auditoriumRowId);
}