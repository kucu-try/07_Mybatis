package com.ohgiraffers.section01.xmlconfig.model;

import com.ohgiraffers.dto.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {
    public List<MenuDTO> selectAllMenu(SqlSession sqlSession){
        return sqlSession.selectList("MenuMapper.selectAllMenu");
    }

    public MenuDTO selectByMenuCode(SqlSession session, int code) {
        return session.selectOne("MenuMapper.selectByMenuCode",code);
    }

    public int registMenu(SqlSession session, MenuDTO menu) {
        return session.insert("MenuMapper.insert" ,menu);
    }

    public int modifyMenu(SqlSession session, MenuDTO menu) {
        return session.update("MenuMapper.modifyMenu",menu);
    }

    public int deleteMenu(SqlSession session, int code) {
        return session.delete("MenuMapper.delete", code);
    }
}
