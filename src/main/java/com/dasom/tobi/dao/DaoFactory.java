package com.dasom.tobi.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration //어플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보
public class DaoFactory {
    @Bean
    public UserDaoJdbc userDao(){
        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
        userDaoJdbc.setDataSource(dataSource());

        return userDaoJdbc;
    }
//    public AccountDao accountDao(){
//        return new UserDaoJdbc(connectionMaker());
//    }
//    public MessageDao MessageDao(){
//        return new UserDaoJdbc(connectionMaker());
//    }
    @Bean // 오브젝트 생성을 담당하는 IoC용 메소드
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker(); //여기만 바꿔주면 된다.
    }
    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/tobi_db");
        dataSource.setUsername("root");
        dataSource.setPassword("26323174");

        return  dataSource;
    }
}
