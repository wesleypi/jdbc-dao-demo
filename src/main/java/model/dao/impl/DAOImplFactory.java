package model.dao.impl;

public class DAOImplFactory {

    public static SellerDAOImpl createSellerDao(){
        return new SellerDAOImpl();
    }

    public static DepartmentDAOImpl createDepartmentDao(){
        return new DepartmentDAOImpl();
    }
}
