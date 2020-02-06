package com.bookmyshow.app.service;

import com.bookmyshow.app.dto.request.AudiLayoutRequest;
import com.bookmyshow.app.model.Auditorium;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.AuditoriumRowRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class CinemaServiceTest {

    private CinemaService cinemaService;

    @Mock
    private AuditoriumRepository auditoriumRepository;

    @Mock
    private AuditoriumRowRepository auditoriumRowRepository;

    @Before
    public void setUp() {
        cinemaService = new CinemaService();
        cinemaService.setAuditoriumRepository(auditoriumRepository);
        cinemaService.setAuditoriumRowRepository(auditoriumRowRepository);
    }

    @Test
    public void testAddCinemaLayout() {
        Auditorium auditorium = new Auditorium();
        Mockito.when(auditoriumRepository.save(Mockito.any())).thenReturn(auditorium);

        AuditoriumRow auditoriumRow = new AuditoriumRow();
        Mockito.when(auditoriumRowRepository.save(Mockito.any())).thenReturn(auditoriumRow);

        String audiId = cinemaService.addCinemaLayout(getAudiLayoutRequest());

        Assert.assertNotNull(audiId);
    }

    private AudiLayoutRequest getAudiLayoutRequest() {
        AudiLayoutRequest audiLayoutRequest = new AudiLayoutRequest();
        audiLayoutRequest.setCinemaId("1");

        List<Map<String, Integer>> noOfRows = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        map.putIfAbsent("A", 4);

        Map<String, Integer> map2 = new HashMap<>();
        map2.putIfAbsent("B", 5);

        Map<String, Integer> map3 = new HashMap<>();
        map3.putIfAbsent("C", 6);

        noOfRows.add(map);
        noOfRows.add(map2);
        noOfRows.add(map3);

        audiLayoutRequest.setNoOfRows(noOfRows);

        return audiLayoutRequest;
    }
}