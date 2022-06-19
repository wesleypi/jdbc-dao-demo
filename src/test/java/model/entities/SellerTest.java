package model.entities;

import org.junit.Assert;
import org.junit.Test;

public class SellerTest {

    @Test
    public void testToString() {
        Department department = new Department()
                .setId(1).setName("Manager");

        Seller seller = new Seller()
                .setId(1).setName("Rodrigo")
                .setEmail("Rodrigo@email.com")
                .setBirthDate(java.sql.Date.valueOf("1999-7-22"))
                .setBaseSalary(3000)
                .setDepartment(department);

        String expected = seller.toString();

        Assert.assertNotEquals(expected, "");
    }

}