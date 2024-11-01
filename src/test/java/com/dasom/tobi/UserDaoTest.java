package com.dasom.tobi;
import com.dasom.tobi.dao.DaoFactory;
import com.dasom.tobi.dao.UserDao;
import com.dasom.tobi.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void DaoTest() throws SQLException, ClassNotFoundException {
        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("mmmzzi");
        user.setName("명지");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId()+"등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId()+"조회성공");

    }
}
