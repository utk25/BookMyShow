package com.bookmyshow.app.service;

import com.bookmyshow.app.convertor.AuditoriumConvertor;
import com.bookmyshow.app.dto.request.AddMovieRequest;
import com.bookmyshow.app.exception.OverlapTimeException;
import com.bookmyshow.app.model.AuditoriumMovieMapping;
import com.bookmyshow.app.repository.AuditoriumMovieRepository;
import com.bookmyshow.app.repository.AuditoriumRepository;
import com.bookmyshow.app.repository.CinemaRepository;
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
public class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private AuditoriumConvertor auditoriumConvertor;

    @Mock
    private AuditoriumRepository auditoriumRepository;

    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private AuditoriumMovieRepository auditoriumMovieRepository;

    private Date startTime = new Date();

    private Date endTime = new Date();

    @Before
    public void setUp() {
        movieService = new MovieService();
        movieService.setAuditoriumRepository(auditoriumRepository);
        movieService.setAuditoriumConvertor(auditoriumConvertor);
        movieService.setCinemaRepository(cinemaRepository);
        movieService.setAuditoriumMovieRepository(auditoriumMovieRepository);
    }

    @Test
    public void testAddMovie() {
        AddMovieRequest addMovieRequest = getAddMovieRequestObject();

        List<AuditoriumMovieMapping> auditoriumMovieMappings = new ArrayList<>();

        Mockito.when(auditoriumMovieRepository.findMovieByTimeRange(startTime, endTime, "1"))
                .thenReturn(auditoriumMovieMappings);

        movieService.addMovie(addMovieRequest);
    }

    @Test(expected = OverlapTimeException.class)
    public void testAddMovieException() {
        AddMovieRequest addMovieRequest = getAddMovieRequestObject();

        AuditoriumMovieMapping auditoriumMovieMapping = new AuditoriumMovieMapping();
        List<AuditoriumMovieMapping> auditoriumMovieMappings = new ArrayList<>();
        auditoriumMovieMappings.add(auditoriumMovieMapping);

        Mockito.when(auditoriumMovieRepository.findMovieByTimeRange(startTime, endTime, "1"))
                .thenReturn(auditoriumMovieMappings);

        movieService.addMovie(addMovieRequest);
    }

    @Test
    public void testGetMovieInfo() {
        AuditoriumMovieMapping auditoriumMovieMapping = new AuditoriumMovieMapping();
        List<AuditoriumMovieMapping> auditoriumMovieMappings = new ArrayList<>();
        auditoriumMovieMappings.add(auditoriumMovieMapping);

        Mockito.when(auditoriumMovieRepository.findMovieByCity(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(auditoriumMovieMappings);

        Assert.assertNotNull(movieService.getMovieInfo(Mockito.anyString(), Mockito.anyString()));
    }

    private AddMovieRequest getAddMovieRequestObject() {
        AddMovieRequest addMovieRequest = new AddMovieRequest();
        addMovieRequest.setAudiId("1");
        addMovieRequest.setCinemaId("2");
        addMovieRequest.setCity("Bengaluru");
        addMovieRequest.setMovieId("3");
        addMovieRequest.setStartTime(startTime);
        addMovieRequest.setEndTime(endTime);
        addMovieRequest.setMovieName("Avengers");

        return addMovieRequest;
    }
}