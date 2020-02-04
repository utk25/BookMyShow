package com.bookmyshow.app.cache;

import com.bookmyshow.app.constants.RedisConstants;
import com.bookmyshow.app.enums.AuditoriumRowPos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void addBookedAuditoriumRowSeat(String auditoriumRowId, AuditoriumRowPos auditoriumRowPos,
                                           Integer seatNumber, String startTime) {
        redisTemplate.opsForValue().set(auditoriumRowId + "_" + auditoriumRowPos.getPos() + "_"
                + startTime + "_" + seatNumber, "1", RedisConstants.BOOKING_ROW_EXPIRY_TIME);
    }

    public int getAuditoriumRowSeatCount(String auditoriumRowId) {
        return redisTemplate.keys(auditoriumRowId + "_*").size();
    }
}