package com.dasom.tobi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll{

    protected PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement preparedStatement = c.prepareStatement("delete from users");
        return  preparedStatement;
    }
}
