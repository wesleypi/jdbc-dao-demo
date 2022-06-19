package model.dao.impl;

import model.DBConnection;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDAOImpl implements SellerDAO {

    protected SellerDAOImpl() {
    }

    @Override
    public void insert(Seller seller) {
        String sql = """
                INSERT INTO seller
                (name, email, "birthDate", "baseSalary", "departmentId")
                VALUES (?,?,?,?,?)""";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDate(3, seller.getBirthDate());
            statement.setBigDecimal(4, seller.getBaseSalary());
            statement.setInt(5, seller.getDepartment().getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();

                    seller.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Seller seller) {
        String sql = """
                UPDATE seller
                SET name= ?, email= ?, "birthDate"= ?, "baseSalary"= ?, "departmentId"= ?
                WHERE id = ?""";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDate(3, seller.getBirthDate());
            statement.setBigDecimal(4, seller.getBaseSalary());
            statement.setInt(5, seller.getDepartment().getId());
            statement.setInt(6, seller.getId());

            statement.executeUpdate();
        } catch (DBException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Seller seller) {
        String sql = """
                DELETE FROM seller
                WHERE id = ?""";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, seller.getId());
            statement.executeUpdate();

        } catch(DBException | SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
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
        return new Seller()
                .setId(resultSet.getInt("Id"))
                .setName(resultSet.getString("Name"))
                .setEmail(resultSet.getString("Email"))
                .setBaseSalary(resultSet.getInt("BaseSalary"))
                .setBirthDate(resultSet.getDate("BirthDate"))
                .setDepartment(department);
    }

    private Seller newInstanceFrom(ResultSet resultSet) throws SQLException {
        Department department = newDepartamentInstance(resultSet);

        return new Seller()
                .setId(resultSet.getInt("Id"))
                .setName(resultSet.getString("Name"))
                .setEmail(resultSet.getString("Email"))
                .setBaseSalary(resultSet.getInt("BaseSalary"))
                .setBirthDate(resultSet.getDate("BirthDate"))
                .setDepartment(department);
    }

    private Department newDepartamentInstance(ResultSet resultSet) throws SQLException {
        return new Department()
                .setId(resultSet.getInt("departmentId"))
                .setName(resultSet.getString("depName"));
    }
}
