package ru.hackathon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {

    private Connection connection;
    private Statement stmt;

    public void connect(DataBase dataBase) throws SQLException {
        connection = DriverManager.getConnection(dataBase.getUrlDB(), dataBase.getUser(), dataBase.getPassword());
        stmt = connection.createStatement();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStmt() {
        return stmt;
    }

}
