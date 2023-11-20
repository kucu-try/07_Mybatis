package com.ohgiraffers.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class template {
    private static SqlSessionFactory sessionFactory;
    public static SqlSession getSqlSession(){
        if(sessionFactory ==null){
            try {
                InputStream inputStream = Resources.getResourceAsStream("xmlconfig/mybatis-config.xml");
                sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory.openSession(false);
    }
}
