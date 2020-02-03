package com.bookmyshow.app.postSetup;

import com.bookmyshow.app.enums.AuditoriumRowPos;
import com.bookmyshow.app.model.Auditorium;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.model.Cinema;
import com.bookmyshow.app.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;


@Component
public class DatabaseSetup implements CommandLineRunner {

    @Autowired
    private CinemaService cinemaService;

    @Override
    public void run(String...args) throws Exception {
        Random random = new Random();

        Cinema cinema = new Cinema();
        cinema.setCinemaId(random.nextInt());
        cinema.setCinemaName("PVR");
        cinema.setCinemaAddress("Kormangala");
        cinema.setCity("Bengaluru");
        cinema.setAuditoriums(new ArrayList<>());

        Auditorium auditorium1 = new Auditorium();
        auditorium1.setAuditoriumId(random.nextInt());
        auditorium1.setCinemaId(cinema.getCinemaId());
        auditorium1.setAuditoriumRows(new ArrayList<>());

        Auditorium auditorium2 = new Auditorium();
        auditorium2.setAuditoriumId(random.nextInt());
        auditorium2.setCinemaId(cinema.getCinemaId());
        auditorium2.setAuditoriumRows(new ArrayList<>());

        cinema.getAuditoriums().add(auditorium1);
        cinema.getAuditoriums().add(auditorium2);

        for (int i = 1; i <= 8; i++) {
            AuditoriumRow auditoriumRow = new AuditoriumRow();
            auditoriumRow.setAuditoriumRowId(random.nextInt());
            auditoriumRow.setBookingStatus(0);
            auditoriumRow.setMaxCapacity(random.nextInt(20));
            switch (i % 4) {
                case 1 :
                    auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.A);
                    break;
                case 2 :
                    auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.B);
                    break;
                case 3 :
                    auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.C);
                    break;
                case 0 :
                    auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.D);
                    break;
                default:
                    break;
            }
            if (i <= 4) {
                auditoriumRow.setAuditoriumId(auditorium1.getAuditoriumId());
                auditorium1.getAuditoriumRows().add(auditoriumRow);
            } else {
                auditoriumRow.setAuditoriumId(auditorium2.getAuditoriumId());
                auditorium2.getAuditoriumRows().add(auditoriumRow);
            }
        }

        cinemaService.addCinemaLayout(cinema);
    }
}
