package com.dasom.tobi.dao;
import com.dasom.tobi.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(DaoFactory.class) //RunWith과 ContextConfiguration을 Junit에선 이렇게 씀
public class UserDaoJdbcTest {
    private UserDaoJdbc dao;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp(){
        this.dao = applicationContext.getBean("userDao", UserDaoJdbc.class);
    }

    @Test
    public void DaoTest() throws SQLException, ClassNotFoundException {

        User user = new User();
        user.setId("heetae");
        user.setName("희태");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + "등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + "조회성공");

    }

    @Test
    public void addAndGet() throws Exception {


        User user1 = new User("aaaa","박명지","1234");
        User user2 = new User("bbbb","박희태","1234");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userGet1 = dao.get(user1.getId());
        assertThat(userGet1.getName()).isEqualTo(user1.getName());
        assertThat(userGet1.getPassword()).isEqualTo(user1.getPassword());

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getName()).isEqualTo(user2.getName());
        assertThat(userGet2.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    public void count() throws Exception {

        User user1 = new User("aaaa","유제승","1234");
        User user2 = new User("bbbb","박희태","1234");
        User user3 = new User("cccc","이현민","1234");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }
    @Test
    public void getUserFailure() throws Exception {

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unknown_id");
        });
    }


}

