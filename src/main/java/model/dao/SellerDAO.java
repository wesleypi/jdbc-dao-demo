package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO extends CrudDAO<Seller> {

    List<Seller> findByDepartment(Department department);
}
