package com.bookmyshow.app.service;

import com.bookmyshow.app.convertor.AuditoriumRowStatusConvertor;
import com.bookmyshow.app.dao.BlockingSeats;
import com.bookmyshow.app.dto.request.BlockSeatRequest;
import com.bookmyshow.app.dto.request.BookingRequestInfo;
import com.bookmyshow.app.dto.response.AuditoriumStatus;
import com.bookmyshow.app.dto.response.AuditoriumStatusResponse;
import com.bookmyshow.app.exception.SameSeatException;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.model.AuditoriumRowStatus;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import com.bookmyshow.app.repository.BookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BlockingSeats blockingSeats;

    @Autowired
    private AuditoriumRowRepository auditoriumRowRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private AuditoriumRowStatusConvertor auditoriumRowStatusConvertor;

    public boolean blockAuditoriumSeats(BlockSeatRequest blockSeatRequest) throws Exception {
        for (BookingRequestInfo bookingRequestInfo : blockSeatRequest.getBooking()) {
            try {
                blockingSeats.acquireLockForSeats(bookingRequestInfo.getAuditoriumRowId(),
                        bookingRequestInfo.getSeatNumber(), blockSeatRequest.getTime());
            } catch (SameSeatException e) {
                for (BookingRequestInfo bookingRequestInfo1 : blockSeatRequest.getBooking()) {
                    if (bookingRequestInfo1.getAuditoriumRowId().equals(bookingRequestInfo.getAuditoriumRowId())) {
                        break;
                    }
                    blockingSeats.releaseRedisLock(bookingRequestInfo1.getAuditoriumRowId(),
                            bookingRequestInfo1.getSeatNumber(), blockSeatRequest.getTime());
                }
                return false;
            }
        }
        return true;
    }

    public AuditoriumStatusResponse getAuditoriumStatus(String auditoriumId, Date startTime) {
        List<AuditoriumRow> auditoriumRowList = auditoriumRowRepository.findAllAuditoriumRowsByAuditoriumId(auditoriumId);
        setAuditoriumRowStatus(startTime, auditoriumRowList);
        return getAuditoriumStatusResponse(auditoriumRowList);
    }

    private void setAuditoriumRowStatus(Date startTime, List<AuditoriumRow> auditoriumRowList) {
        for (AuditoriumRow auditoriumRow : auditoriumRowList) {
            String auditoriumRowId = auditoriumRow.getAuditoriumRowId();
            AuditoriumRowStatus auditoriumRowStatus = bookingStatusRepository.
                    findRowStatusByStartTime(startTime, auditoriumRowId);
            int statusFromDB = 0;
            if(auditoriumRowStatus == null) {
                bookingStatusRepository.save(auditoriumRowStatusConvertor
                        .createAuditoriumRowStatus(auditoriumRow, startTime));
            } else {
                statusFromDB = auditoriumRowStatus.getStatus();
            }

            int statusInCache = 0;
            for (int seat = 1; seat <= auditoriumRow.getMaxCapacity(); seat++) {
                if (blockingSeats.getSeatStatus(auditoriumRowId, String.valueOf(seat), startTime)) {
                    statusInCache |= 1 << (seat - 1);
                }
            }
            int finalStatus = statusFromDB | statusInCache;
            auditoriumRow.setBookingStatus(finalStatus);
        }
    }

    private AuditoriumStatusResponse getAuditoriumStatusResponse(List<AuditoriumRow> auditoriumRows) {
        List<AuditoriumStatus> auditoriumStatuses = new ArrayList<>();
        for (AuditoriumRow auditoriumRow : auditoriumRows) {
            AuditoriumStatus auditoriumStatus = AuditoriumStatus.builder().status(auditoriumRow.getBookingStatus())
                    .auditoriumRowId(auditoriumRow.getAuditoriumRowId()).capacity(auditoriumRow.getMaxCapacity()).build();
            auditoriumStatuses.add(auditoriumStatus);
        }
        return AuditoriumStatusResponse.builder().auditoriumStatuses(auditoriumStatuses).build();
    }

    public BlockingSeats getBlockingSeats() {
        return blockingSeats;
    }

    public void setBlockingSeats(BlockingSeats blockingSeats) {
        this.blockingSeats = blockingSeats;
    }

    public AuditoriumRowRepository getAuditoriumRowRepository() {
        return auditoriumRowRepository;
    }

    public void setAuditoriumRowRepository(AuditoriumRowRepository auditoriumRowRepository) {
        this.auditoriumRowRepository = auditoriumRowRepository;
    }

    public BookingStatusRepository getBookingStatusRepository() {
        return bookingStatusRepository;
    }

    public void setBookingStatusRepository(BookingStatusRepository bookingStatusRepository) {
        this.bookingStatusRepository = bookingStatusRepository;
    }

    public AuditoriumRowStatusConvertor getAuditoriumRowStatusConvertor() {
        return auditoriumRowStatusConvertor;
    }

    public void setAuditoriumRowStatusConvertor(AuditoriumRowStatusConvertor auditoriumRowStatusConvertor) {
        this.auditoriumRowStatusConvertor = auditoriumRowStatusConvertor;
    }
}