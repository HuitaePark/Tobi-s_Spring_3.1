package com.dasom.tobi.dao;

import com.dasom.tobi.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;


import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private DataSource dataSource;
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void add(final User user) throws ClassNotFoundException, SQLException {

        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement preparedStatement = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");

                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());

                return preparedStatement;
            }
        });
    }

    public void deleteAll() throws Exception {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy(){
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement preparedStatement = c.prepareStatement("delete from users");
                        return preparedStatement;
                    }
                });
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

    public int getCount() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection c = null;

        try{
             c = dataSource.getConnection();
            preparedStatement = c.prepareStatement("select count(*) from users");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        }catch (SQLException e){
        throw e;
       }finally {
            if(resultSet!=null){try{resultSet.close();}catch(SQLException e){}}
            if(preparedStatement!=null){try{preparedStatement.close();}catch(SQLException e){}}
            if(c!=null){try{c.close();}catch(SQLException e){}}
        }
    }



}
