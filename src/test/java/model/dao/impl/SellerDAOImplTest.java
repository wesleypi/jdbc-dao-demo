package model.dao.impl;

import model.entities.Seller;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class SellerDAOImplTest {

    @Test
    public void findById(){

    }

    @Test
    public void findAll() {
        SellerDAOImpl dao = DAOImplFactory.createSellerDao();

        List<Seller> sellers = dao.findAll();

        Assert.assertNotNull(sellers);
    }
}