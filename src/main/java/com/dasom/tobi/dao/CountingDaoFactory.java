package com.dasom.tobi.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public UserDaoJdbc userDao(ConnectionMaker connectionMaker){
        return new UserDaoJdbc();
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(realConnectionMaker());
    }
    @Bean
    public ConnectionMaker realConnectionMaker(){
        return new DConnectionMaker();
    }
}
