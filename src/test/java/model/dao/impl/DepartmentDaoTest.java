package model.dao.impl;

import model.entities.Department;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDaoTest {

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
        DepartmentDao dao = FactoryDao.createDepartmentDao();

        Department department = dao.findById(1);

        System.out.println(department);
    }

    @Test
    public void findAll() {
        DepartmentDao dao = FactoryDao.createDepartmentDao();

        List<Department> departments = dao.findAll();

        departments.forEach(System.out::println);
    }
}