package com.dasom.tobi;

import com.dasom.tobi.dao.ConnectionMaker;
import com.dasom.tobi.dao.DConnectionMakeer;
import com.dasom.tobi.dao.UserDao;
import com.dasom.tobi.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TobiApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(TobiApplication.class, args);

        ConnectionMaker DConnectionMaker = new DConnectionMakeer(); // 어디 회사의 DB든 커넥션 메이커만 연결해주면 됨
        UserDao dao = new UserDao(DConnectionMaker); // N회사의 DB면 NConnentionMaker로 바꿔주면 된다

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
