package com.dasom.tobi.dao;

import com.dasom.tobi.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;


import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private DataSource dataSource;


    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }
    public User get(String id) throws ClassNotFoundException,SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        rs.close();
        ps.close();
        c.close();
        if(user == null) throw new EmptyResultDataAccessException(1);
        return user;
    }
    public void deleteAll() throws Exception {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            c = dataSource.getConnection();
            preparedStatement = c.prepareStatement("delete from users");
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            throw e;
        }
        finally {
            if (preparedStatement !=null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){

                }
            }
            if (c != null){
                try{c.close();}catch (SQLException e){}
            }
        }
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();
        return count;
    }

}
