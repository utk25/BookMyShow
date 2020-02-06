package com.bookmyshow.app.convertor;


import com.bookmyshow.app.enums.AuditoriumRowPos;
import com.bookmyshow.app.model.AuditoriumRow;
import com.bookmyshow.app.model.AuditoriumRowStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class AuditoriumRowStatusConvertorTest {

    private AuditoriumRowStatusConvertor auditoriumRowStatusConvertor;

    private Date startTime;

    @Before
    public void setUp() {
        auditoriumRowStatusConvertor = new AuditoriumRowStatusConvertor();
        startTime = new Date();
    }

    @Test
    public void verifyAuditoriumRowStatusObject() {
        AuditoriumRow auditoriumRow = getAuditoriumRowObject();

        AuditoriumRowStatus auditoriumRowStatus = auditoriumRowStatusConvertor
                .createAuditoriumRowStatus(auditoriumRow, startTime);

        Assert.assertEquals(new Integer(0), auditoriumRowStatus.getStatus());
        Assert.assertEquals("1", auditoriumRowStatus.getAuditoriumRowId());
        Assert.assertEquals(AuditoriumRowPos.A, auditoriumRowStatus.getAuditoriumRowPos());
        Assert.assertEquals(startTime, auditoriumRowStatus.getStartTime());

    }

    private AuditoriumRow getAuditoriumRowObject() {
        AuditoriumRow auditoriumRow = new AuditoriumRow();
        auditoriumRow.setBookingStatus(0);
        auditoriumRow.setMaxCapacity(10);
        auditoriumRow.setAuditoriumId("3");
        auditoriumRow.setAuditoriumRowId("1");
        auditoriumRow.setAuditoriumRowPos(AuditoriumRowPos.A);

        return auditoriumRow;
    }
}