package model.dao.impl;

import model.entities.Seller;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SellerDAOImplTest {

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
        String expected = """
                Seller {
                 id = 3 , name = Alex Grey , email = alex@gmail.com
                 birthDay = 1988-01-15 , baseSalary=2200
                 Department { id = 1, name = Computers }
                }""";

        SellerDAOImpl dao = DAOImplFactory.createSellerDao();

        Seller seller = dao.findById(3);

        Assert.assertEquals(expected, seller.toString());

    }

    @Test
    public void findByDepartment(){
        String expected = """
                Seller {
                 id = 3 , name = Alex Grey , email = alex@gmail.com
                 birthDay = 1988-01-15 , baseSalary=2200
                 Department { id = 1, name = Computers }
                }""";

        SellerDAOImpl dao = DAOImplFactory.createSellerDao();

        DepartmentDAOImpl dep = DAOImplFactory.createDepartmentDao();

        List<Seller> seller = dao.findByDepartment(dep.findById(1));

        seller.forEach(System.out::println);
//        Assert.assertEquals(expected, seller.toString());
    }

    @Test
    public void findAll() {
        SellerDAOImpl dao = DAOImplFactory.createSellerDao();

        List<Seller> sellers = dao.findAll();

        sellers.forEach(System.out::println);
    }
}