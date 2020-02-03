/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.repository;

import org.springframework.data.repository.query.Param;

public interface MovieCinemaRepository {

    public void findMovieInAllCinema(@Param("movie_Id") Integer movieId,
                                     @Param("city") String city);
}