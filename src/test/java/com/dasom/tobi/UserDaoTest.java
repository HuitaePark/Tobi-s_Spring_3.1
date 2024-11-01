package com.dasom.tobi;
import com.dasom.tobi.dao.DaoFactory;
import com.dasom.tobi.dao.UserDao;
import com.dasom.tobi.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void DaoTest() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = ac.getBean("userDao",UserDao.class);

        User user = new User();
        user.setId("heetae");
        user.setName("희태");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId()+"등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId()+"조회성공");

    }
}
