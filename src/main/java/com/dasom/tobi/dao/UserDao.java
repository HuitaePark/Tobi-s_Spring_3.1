package com.dasom.tobi.dao;

import com.dasom.tobi.domain.User;

import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }
    public User get(String id) throws ClassNotFoundException,SQLException{
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
    private Connection getConnection() throws ClassNotFoundException,SQLException{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tobi_db", "root", "26323174");
        return c;
    }
}
