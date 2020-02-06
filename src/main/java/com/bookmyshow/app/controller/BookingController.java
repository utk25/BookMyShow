package com.bookmyshow.app.controller;

import com.bookmyshow.app.dto.request.BlockSeatRequest;
import com.bookmyshow.app.dto.response.AuditoriumStatusResponse;
import com.bookmyshow.app.dto.response.BaseResponse;
import com.bookmyshow.app.dto.response.BlockSeatResponse;
import com.bookmyshow.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/movie/status/{auditoriumId}/timing")
    public ResponseEntity<BaseResponse> getAuditoriumStatus(@PathVariable String auditoriumId,
                                                            @RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    Date startTime) {
        AuditoriumStatusResponse auditoriumStatusResponse = bookingService.getAuditoriumStatus(auditoriumId, startTime);
        return new ResponseEntity<>(BaseResponse.builder().data(auditoriumStatusResponse)
                .statusCode("0").build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movie/blockSeats")
    public ResponseEntity<BaseResponse> blockAuditoriumSeats(@RequestBody BlockSeatRequest blockSeatRequest) throws Exception {
        boolean isBlocked = bookingService.blockAuditoriumSeats(blockSeatRequest);
        if (isBlocked) {
            return new ResponseEntity<>(BaseResponse.builder().data(BlockSeatResponse.builder().
                    message("Blocked successfully").isBlocked(true).build()).statusCode("0").build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(BaseResponse.builder().data(BlockSeatResponse.builder().
                message("Could not block successfully").isBlocked(false).build()).statusCode("0").build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}