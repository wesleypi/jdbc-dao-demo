package model.dao.impl;

import model.DBConnection;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAOImpl implements SellerDAO {

    protected SellerDAOImpl(){}

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
                    SELECT seller.*, department.name as depName
                    FROM seller INNER JOIN department
                    ON department.id = seller."departmentId"
                    WHERE seller.id = ?""";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {

                if(resultSet.next())
                    return newInstanceFrom(resultSet);

            }
        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
        return new Seller();
    }

    @Override
    public List<Seller> findAll() {
        ArrayList<Seller> Sellers = new ArrayList<>();

        String sql = """
                SELECT seller.*, department.name as depName
                FROM seller INNER JOIN department
                ON department.Id = seller."departmentId"
                ORDER BY name
                """;

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Sellers.add(newInstanceFrom(resultSet));
                }
            }

        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
        return Sellers;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        ArrayList <Seller> sellers = new ArrayList<>();
        String sql = """
                    SELECT seller.*, department.name as depName
                    FROM seller INNER JOIN department
                    ON department.id = seller."departmentId"
                    WHERE department.id = ?
                    ORDER BY name""";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,department.getId());
            try(ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()){
                    sellers.add(newInstanceFrom(resultSet,department));
                }

            }
        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        } 
        return sellers;
    }
    private Seller newInstanceFrom(ResultSet resultSet, Department department) throws SQLException{
        return Seller.Builder.newInstance()
                .setId(resultSet.getInt("Id"))
                .setName(resultSet.getString("Name"))
                .setEmail(resultSet.getString("Email"))
                .setBaseSalary(resultSet.getInt("BaseSalary"))
                .setBirthDay(resultSet.getDate("BirthDate"))
                .setDepartment(department).build();
    }

    private Seller newInstanceFrom(ResultSet resultSet) throws SQLException {
        Department department = newDepartamentInstance(resultSet);

        return Seller.Builder.newInstance()
                .setId(resultSet.getInt("Id"))
                .setName(resultSet.getString("Name"))
                .setEmail(resultSet.getString("Email"))
                .setBaseSalary(resultSet.getInt("BaseSalary"))
                .setBirthDay(resultSet.getDate("BirthDate"))
                .setDepartment(department).build();
    }

    private Department newDepartamentInstance(ResultSet resultSet) throws SQLException {
        return Department.Builder.newInstance()
                .setId(resultSet.getInt("departmentId"))
                .setName(resultSet.getString("depName")).build();
    }
}
