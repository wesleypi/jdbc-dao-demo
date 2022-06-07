package model.dao.impl;

import model.FactoryConnection;
import model.dao.Dao;
import model.entities.Department;
import model.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements Dao<Department> {

    protected DepartmentDao(){
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void delete(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {

        String sql = """
                SELECT *
                FROM "Department"
                WHERE "Id" = ?""";

        try(Connection connection = FactoryConnection.getConnection();
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
        return Department.Builder.newInstance()
                    .setId(resultSet.getInt("Id"))
                    .setName(resultSet.getString("Name")).build();
    }

    @Override
    public List<Department> findAll() {
        String sql = """
                SELECT *
                FROM "Department"
                """;

        ArrayList<Department> departments = new ArrayList<>();
        try(Connection connection = FactoryConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()){
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

