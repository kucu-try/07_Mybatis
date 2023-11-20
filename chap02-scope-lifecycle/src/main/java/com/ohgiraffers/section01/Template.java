package com.ohgiraffers.section01;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    /*
    * SqlSessionFactory 는 애플리케이션에 실행하는 동안 존재한다.
    * 애플리케이션이 실행되는 동안 여러 차례 SqlSessionFactory 를 다시 빌드하지 않는 것이 가장 좋은 형태이다.
    * 애플리케이션 스코프로 관리하ㅏ기 위한 가장 간단한 방법은 싱글톤 패턴을 이용하는 것읻.
    * */
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession(){

        /*
        * SqlSessionFactoryBuilder 는 sqlSessionFactory 를 생성한 후 유짛ㄹ 필요가 없ㄷ.
        * 따라서 메소드 스코프로 생명주기를 관리하여 메소득 종료된 이후 gc가비지 컬렉터 가 회숳ㄹ 수 있도록 ㅎ는 것읻.
        * 여러개의 sqlSessionFactory 를 빌드하기 위해 재상용할 수 있지만 sqlSessionFactory를 여러개 생성하지 않는 것이
        * 권장사항이며 SqlSessionFactoryBuilder 는 필요시에만 메서드 레벨에서 잠시 호출하는 것이 바람직하다.
        * */
        if (sqlSessionFactory == null){
            String resource = "mybatis-config.xml";

            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory.openSession(false);
    }
}
