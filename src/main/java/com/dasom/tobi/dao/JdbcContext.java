package com.dasom.tobi.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;


    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public void workWithStatementStrategy(StatementStrategy smt) throws SQLException{
        Connection c = null;
        PreparedStatement preparedStatement = null;
        try{
            c = dataSource.getConnection();
            preparedStatement = smt.makePreparedStatement(c);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw e;
        }finally {
            if(preparedStatement!=null){try{preparedStatement.close();}catch(SQLException e){}}
            if(c!=null){try{c.close();}catch(SQLException e){}}
        }
    }
    public void executeSql(final String query) throws SQLException{
        workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement(query);
                    }
                }
        );
    }
}
