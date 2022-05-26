package model.entities;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class SellerTest {

    @Test
    public void testToString() {
        Department department = new Department(1, "Manager");
        Seller seller = new Seller(1,"Rodrigo","Rodrigo@email.com",new Date(),new BigDecimal(3000), department);

        String expected = seller.toString();

        Assert.assertNotEquals(expected, "");
    }
}