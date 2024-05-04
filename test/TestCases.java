package test;

import org.junit.Assert;
import org.junit.Test;
import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.Database.Prepared.AdminReservationReport;

public class TestCases{

    @Test
    public void testUserID() {
        User user = new User();
        user.setUserId(52);
        Assert.assertEquals(52, user.getUserId());
    }
}