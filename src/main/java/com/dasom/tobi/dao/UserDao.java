package com.dasom.tobi.dao;

import com.dasom.tobi.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper =
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            };

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(final User user) throws ClassNotFoundException, SQLException {

        this.jdbcTemplate.update("insert into users(id,name,password) values(?,?,?)",
                user.getId(),user.getName(),user.getPassword());
    }

    public void deleteAll() throws Exception {
       this.jdbcTemplate.update("delete from users");
    }

    public User get(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return this.jdbcTemplate.queryForObject(sql, userMapper, id);
    }

    public int getCount() {
        String sql = "SELECT COUNT(*) FROM users";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        return this.jdbcTemplate.query(sql, userMapper);
    }
}
