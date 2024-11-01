package com.dasom.tobi.dao;

public class DaoFactory {
    public UserDao userDao(){
       return new UserDao(connectionMaker());
    }
//    public AccountDao accountDao(){
//        return new UserDao(connectionMaker());
//    }
//    public MessageDao MessageDao(){
//        return new UserDao(connectionMaker());
//    }
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker(); //여기만 바꿔주면 된다.
    }
}
