package com.bookmyshow.app.repository;

import com.bookmyshow.app.model.AuditoriumMovieMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AuditoriumMovieRepository extends CrudRepository<AuditoriumMovieMapping, Integer> {

    @Query("select distinct am " +
            "from AuditoriumMovieMapping am " +
            "where (am.startTime between :start_Time and :end_Time  " +
            "or am.endTime between :start_Time and :end_Time) " +
            " and am.audiId = :audi_Id")
    List<AuditoriumMovieMapping> findMovieByTimeRange(@Param("start_Time") Date startTime,
                                                      @Param("end_Time") Date endTime,
                                                      @Param("audi_Id") String audiId);

    @Query("select distinct am " +
            "from AuditoriumMovieMapping am " +
            "where am.movieId = :movie_Id and am.city = :city_Id")
    List<AuditoriumMovieMapping> findMovieByCity(@Param("city_Id") String cityId,
                                                 @Param("movie_Id") String movieId);
}