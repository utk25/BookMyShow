package com.bookmyshow.app.controller;

import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/movie/status/{auditoriumId}/{startTime}")
    public List<AuditoriumRow> getAuditoriumStatus(@PathVariable String auditoriumId,
                                                   @PathVariable Date startTime) {
        return bookingService.getAuditoriumStatus(auditoriumId, startTime);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movie/block")
    public String blockAuditoriumRows() {
        bookingService.blockAuditoriumRows();
        return "";
    }
}