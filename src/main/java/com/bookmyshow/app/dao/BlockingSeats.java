package com.bookmyshow.app.dao;


import com.bookmyshow.app.constants.RedisConstants;
import com.bookmyshow.app.exception.SameSeatException;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Data
public class BlockingSeats {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void acquireLockForSeats(String auditoriumRowId, String seatNumber, Date startTime) throws Exception {
        String lockKey = getRedisLockKey(auditoriumRowId, seatNumber, startTime);
        if (!StringUtil.isNullOrEmpty(redisTemplate.opsForValue().get(lockKey))) {
            throw new SameSeatException("Same seat being booked");
        }
        redisTemplate.opsForValue().set(lockKey, "1", RedisConstants.BOOKING_ROW_EXPIRY_TIME, TimeUnit.MINUTES);
    }

    private String getRedisLockKey(String auditoriumRowId, String seatNumber, Date startTime) {
        return RedisConstants.REDIS_LOCK + auditoriumRowId + "_" + seatNumber + "_" + startTime;
    }

    public void releaseRedisLock(String auditoriumRowId, String seatNumber, Date startTime) throws Exception {
        String lockKey = getRedisLockKey(auditoriumRowId, seatNumber, startTime);

        redisTemplate.opsForValue().getOperations().delete(lockKey);
    }

    public boolean getSeatStatus(String auditoriumRowId, String seatNumber, Date startTime) {
        String lockKey = getRedisLockKey(auditoriumRowId, seatNumber, startTime);
        return !StringUtil.isNullOrEmpty(redisTemplate.opsForValue().get(lockKey));
    }
}