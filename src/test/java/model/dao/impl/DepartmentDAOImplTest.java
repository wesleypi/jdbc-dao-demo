package model.dao.impl;

import model.entities.Department;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepartmentDAOImplTest {

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
        DepartmentDAOImpl dao = DAOImplFactory.createDepartmentDao();

        Department department = dao.findById(4);

        Assert.assertEquals("Department{id=4, name='Books'}",department.toString());

        System.out.println(department+"\n");
    }

    @Test
    public void findAllMustBeNotNull() {
        DepartmentDAOImpl dao = DAOImplFactory.createDepartmentDao();

        List<Department> departments = dao.findAll();

        Assert.assertNotNull(departments);

        departments.forEach(System.out::println);
    }


}