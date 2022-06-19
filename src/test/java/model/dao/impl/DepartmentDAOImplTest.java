package model.dao.impl;

import model.entities.Department;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepartmentDAOImplTest {

    @Test
    public void insert() {
        Department department = new Department()
                .setName("Teste");

        DepartmentDAOImpl dao = DAOImplFactory.createDepartmentDao();

        System.out.println(department);

        dao.insert(department);

        System.out.println(department);
    }

    @Test
    public void update() {
        DepartmentDAOImpl dao = DAOImplFactory.createDepartmentDao();

        Department department = new Department()
                .setId(4)
                .setName("Movies");

        dao.update(department);

        Assert.assertEquals("Movies", dao.findById(4).getName());

        department.setName("Books");

        dao.update(department);

        Assert.assertEquals("Books", dao.findById(4).getName());
    }

    @Test
    public void findById() {
        DepartmentDAOImpl dao = DAOImplFactory.createDepartmentDao();

        Department department = dao.findById(4);

        Assert.assertEquals("Department { id = 4, name = Books }",department.toString());

    }

    @Test
    public void findAll() {
        DepartmentDAOImpl dao = DAOImplFactory.createDepartmentDao();

        List<Department> departments = dao.findAll();

        Assert.assertNotNull(departments);
    }

}