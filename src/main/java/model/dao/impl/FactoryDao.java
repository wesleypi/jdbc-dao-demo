package model.dao.impl;

import model.FactoryConnection;

public class FactoryDao {

    public static SellerDao createSellerDao(){
        return new SellerDao();
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDao();
    }
}
