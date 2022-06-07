package model.entities;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SellerTest {

    @Test
    public void testToString() {
        Department department = Department.Builder.newInstance()
                .setId(1).setName("Manager").build();

        Seller seller = Seller.Builder.newInstance()
                .setId(1).setName("Rodrigo")
                .setEmail("Rodrigo@email.com")
                .setBirthDay(new Date())
                .setBaseSalary(3000)
                .setDepartment(department).build();

        String expected = seller.toString();

        Assert.assertNotEquals(expected, "");
    }

}