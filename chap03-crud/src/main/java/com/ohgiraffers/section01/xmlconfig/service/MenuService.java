package com.ohgiraffers.section01.xmlconfig.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.model.MenuDAO;
import org.apache.ibatis.session.SqlSession;

import static com.ohgiraffers.common.template.*;
import java.util.List;

public class MenuService {
    private final MenuDAO menuDAO;

    public MenuService() {
        this.menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu(){
        SqlSession session = getSqlSession();
        List<MenuDTO> menuList = menuDAO.selectAllMenu(session);
        session.close();
        return menuList;
    }

    public MenuDTO selectByMenuCode(int code) {
        SqlSession session = getSqlSession();
        MenuDTO menu = menuDAO.selectByMenuCode(session,code);
        session.close();
        return menu;
    }

    public boolean registMenu(MenuDTO menu) {
        SqlSession session = getSqlSession();

        int result = menuDAO.registMenu(session,menu);

        session.close();

        if (result > 0){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();

        return result>0? true:false;
    }

    public boolean modifyMenu(MenuDTO menu) {
        SqlSession session = getSqlSession();

        int result = menuDAO.modifyMenu(session,menu);

        if (result > 0){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();

        return result>0? true:false;
    }

    public boolean deleteMenu(int code) {
        SqlSession session = getSqlSession();
        int result = menuDAO.deleteMenu(session,code);

        if(result > 0 ){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();

        return result>0? true:false;
    }
}
