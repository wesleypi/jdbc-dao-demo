package model.entities;

import org.junit.Assert;
import org.junit.Test;

public class DepartmentTest {

    @Test
    public void testToString() {
        Department department = new Department(1,"Manager");


        String expected = "Department{id=1, name='Manager'}";

        Assert.assertEquals(expected, department.toString());
    }
}