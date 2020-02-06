package com.bookmyshow.app.service;

import com.bookmyshow.app.convertor.AuditoriumRowStatusConvertor;
import com.bookmyshow.app.dao.BlockingSeats;
import com.bookmyshow.app.dto.request.BlockSeatRequest;
import com.bookmyshow.app.dto.request.BookingRequestInfo;
import com.bookmyshow.app.dto.response.AuditoriumStatusResponse;
import com.bookmyshow.app.enums.AuditoriumRowPos;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import com.bookmyshow.app.repository.BookingStatusRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class BookingServiceTest {

    private BookingService bookingService;

    @Mock
    private BlockingSeats blockingSeats;

    @Mock
    private AuditoriumRowRepository auditoriumRowRepository;

    @Mock
    private BookingStatusRepository bookingStatusRepository;

    @Mock
    private AuditoriumRowStatusConvertor auditoriumRowStatusConvertor;

    @Before
    public void setUp() {
        bookingService = new BookingService();
        bookingService.setAuditoriumRowRepository(auditoriumRowRepository);
        bookingService.setAuditoriumRowStatusConvertor(auditoriumRowStatusConvertor);
        bookingService.setBookingStatusRepository(bookingStatusRepository);
        bookingService.setBlockingSeats(blockingSeats);
    }

    @Test
    public void testBlockAuditoriumSeatsTrue() throws Exception {
        BlockSeatRequest blockSeatRequest = new BlockSeatRequest();

        BookingRequestInfo bookingRequestInfo1 = new BookingRequestInfo();
        bookingRequestInfo1.setAuditoriumRowId("1");
        bookingRequestInfo1.setSeatNumber("1");

        BookingRequestInfo bookingRequestInfo2 = new BookingRequestInfo();
        bookingRequestInfo2.setAuditoriumRowId("1");
        bookingRequestInfo2.setSeatNumber("2");

        List<BookingRequestInfo> bookingRequestInfos = new ArrayList<>();
        bookingRequestInfos.add(bookingRequestInfo1);
        bookingRequestInfos.add(bookingRequestInfo2);

        final Date startTime = Mockito.mock(Date.class);

        blockSeatRequest.setBooking(bookingRequestInfos);
        blockSeatRequest.setTime(startTime);
        blockSeatRequest.setAudiToriumId("1");

        Assert.assertTrue(bookingService.blockAuditoriumSeats(blockSeatRequest));
    }

    @Test
    public void testGetAuditoriumStatus() {
        AuditoriumRow auditoriumRow = new AuditoriumRow();
        auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.A);
        auditoriumRow.setAuditoriumRowId("1");
        auditoriumRow.setAuditoriumId("1");
        auditoriumRow.setMaxCapacity(10);
        auditoriumRow.setBookingStatus(0);

        List<AuditoriumRow> auditoriumRows = new ArrayList<>();
        auditoriumRows.add(auditoriumRow);

        Date startTime = new Date();

        Mockito.when(auditoriumRowRepository.findAllAuditoriumRowsByAuditoriumId(Mockito.anyString()))
                .thenReturn(auditoriumRows);

        AuditoriumStatusResponse auditoriumStatusResponse = bookingService
                .getAuditoriumStatus(Mockito.anyString(), startTime);

        Assert.assertNotNull(auditoriumStatusResponse);
    }
}