package com.dasom.tobi.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //어플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보
public class DaoFactory {
    @Bean
    public UserDao userDao(){
       return new UserDao(connectionMaker());
    }
//    public AccountDao accountDao(){
//        return new UserDao(connectionMaker());
//    }
//    public MessageDao MessageDao(){
//        return new UserDao(connectionMaker());
//    }
    @Bean // 오브젝트 생성을 담당하는 IoC용 메소드
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker(); //여기만 바꿔주면 된다.
    }
}
