package test;

import org.junit.Assert;
import org.junit.Test;
import com.hotelreservationapp.models.Database.Tables.User;

public class TestCases{

    @Test
    public void testUserID() {
        User user = new User();
        user.setUserId(52);
        Assert.assertEquals(52, user.getUserId());
    }

}