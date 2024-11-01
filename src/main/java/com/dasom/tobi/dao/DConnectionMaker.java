package com.dasom.tobi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tobi_db", "root", "26323174");
        return c;
    }
}
