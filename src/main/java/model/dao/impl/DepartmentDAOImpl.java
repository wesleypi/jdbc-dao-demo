package model.dao.impl;

import model.DBConnection;
import model.dao.DepartmentDAO;
import model.entities.Department;
import model.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    protected DepartmentDAOImpl(){
    }

    @Override
    public void insert(Department department) {
        String sql = """
                INSERT INTO department
                (Name) VALUES (?)""";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, department.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    department.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Department department) {
        String sql = """
                UPDATE department
                SET Name = ?
                WHERE id = ?""";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,department.getName());
            statement.setInt(2,department.getId());

            statement.executeUpdate();
        } catch (DBException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Department department) {
        String sql = """
                DELETE FROM department
                WHERE id = ?""";
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, department.getId());
            statement.executeUpdate();

        } catch (DBException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public Department findById(Integer id) {

        String sql = """
                SELECT *
                FROM department
                WHERE id = ?""";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1,id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next())
                    return buildInstanceFrom(resultSet);
            }

        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
        return new Department();
    }

    private Department buildInstanceFrom(ResultSet resultSet) throws SQLException {
        return new Department()
                    .setId(resultSet.getInt("Id"))
                    .setName(resultSet.getString("Name"));
    }

    @Override
    public List<Department> findAll() {
        ArrayList<Department> departments = new ArrayList<>();

        String sql = """
                SELECT *
                FROM Department""";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    departments.add(buildInstanceFrom(resultSet));
                }
            }

        } catch (SQLException | DBException exception) {
            exception.printStackTrace();
        }
        return departments;
    }

}

