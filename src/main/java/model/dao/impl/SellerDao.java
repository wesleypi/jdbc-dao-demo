package model.dao.impl;

import model.FactoryConnection;
import model.dao.Dao;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDao implements Dao<Seller> {

    protected SellerDao(){}

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void delete(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        String sql = """
                    SELECT public."Seller",department.Name as DepName
                    FROM seller INNER JOIN department
                    ON seller.DepartmentId department.Id
                    WHERE seller.Id = ?""";

        try(Connection connection = FactoryConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {

                if(resultSet.next())
                    return buildInstanceFrom(resultSet);

            }
        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    private Seller buildInstanceFrom(ResultSet resultSet) throws SQLException {
        Department department = Department.Builder.newInstance()
                .setId(resultSet.getInt("DepartmentId"))
                .setName(resultSet.getString("DepName")).build();

        return Seller.Builder.newInstance()
                .setId(resultSet.getInt("Id"))
                .setName(resultSet.getString("Name"))
                .setEmail(resultSet.getString("Email"))
                .setBaseSalary(resultSet.getInt("BaseSalary"))
                .setBirthDay(resultSet.getDate("BirthDate"))
                .setDepartment(department).build();
    }
}
