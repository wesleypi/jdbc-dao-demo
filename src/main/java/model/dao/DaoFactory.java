package model.dao;

import model.dao.impl.DepartmentDao;
import model.dao.impl.SellerDao;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDao();
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDao();
    }
}
