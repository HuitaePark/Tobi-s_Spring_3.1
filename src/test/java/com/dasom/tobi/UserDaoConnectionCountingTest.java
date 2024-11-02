package com.dasom.tobi;

import com.dasom.tobi.dao.CountingConnectionMaker;
import com.dasom.tobi.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    @Test
    public void CountingTest() throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        UserDao dao = ac.getBean("userDao",UserDao.class);
        //
        // DAO 사용 코드
        //
        CountingConnectionMaker ccm = ac.getBean("connectionmaker",CountingConnectionMaker.class);
        System.out.println("커넥션 카운터는 : "+ccm.getCounter());
    }
}
