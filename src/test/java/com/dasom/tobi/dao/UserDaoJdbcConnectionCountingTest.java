package com.dasom.tobi.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoJdbcConnectionCountingTest {
    @Test
    public void CountingTest() throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        UserDaoJdbc dao = ac.getBean("userDao", UserDaoJdbc.class);
        //
        // DAO 사용 코드
        //
        CountingConnectionMaker ccm = ac.getBean("connectionmaker",CountingConnectionMaker.class);
        System.out.println("커넥션 카운터는 : "+ccm.getCounter());
    }
}
