package com.dasom.tobi;

import com.dasom.tobi.dao.UserDao;
import com.dasom.tobi.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TobiApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(TobiApplication.class, args);

        UserDao dao = new UserDao();

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
