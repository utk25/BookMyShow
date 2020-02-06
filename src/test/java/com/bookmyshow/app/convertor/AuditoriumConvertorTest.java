package com.bookmyshow.app.convertor;

import com.bookmyshow.app.dto.request.AddMovieRequest;
import com.bookmyshow.app.model.AuditoriumMovieMapping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class AuditoriumConvertorTest {

    private AuditoriumConvertor auditoriumConvertor;

    private Date startTime;

    private Date endTime;

    @Before
    public void setUp() {
        auditoriumConvertor = new AuditoriumConvertor();
        startTime = new Date();
        endTime = new Date();
    }

    @Test
    public void verifyAuditoriumMovieMappingObject() {
        AddMovieRequest addMovieRequest = getAddMovieRequestObject();

        AuditoriumMovieMapping auditoriumMovieMapping1 = auditoriumConvertor
                .createAuditoriumMovieMapping(addMovieRequest);

        Assert.assertEquals("Bengaluru", auditoriumMovieMapping1.getCity());
        Assert.assertEquals("1", auditoriumMovieMapping1.getAudiId());
        Assert.assertEquals(endTime, auditoriumMovieMapping1.getEndTime());
        Assert.assertEquals("3", auditoriumMovieMapping1.getMovieId());
        Assert.assertEquals(startTime, auditoriumMovieMapping1.getStartTime());
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