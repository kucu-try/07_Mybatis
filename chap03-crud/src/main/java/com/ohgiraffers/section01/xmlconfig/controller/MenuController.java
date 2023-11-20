package com.ohgiraffers.section01.xmlconfig.controller;

import com.ohgiraffers.common.PrintResult;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.service.MenuService;
import static com.ohgiraffers.common.template.*;
import java.util.List;
import java.util.Map;

public class MenuController {
    private final PrintResult printResult;
    private final MenuService menuService;

    public MenuController() {
        this.printResult = new PrintResult();
        this.menuService = new MenuService();
    }

    public void selectAllMenu(){
        List<MenuDTO> menuList = menuService.selectAllMenu();
        if (menuList != null){
            printResult.printMenuList(menuList);
        }else {
            printResult.printErrorMessage("selectList");
        }
    }

    public void selectMenuByCode(Map<String, String> parameter){
        int code = Integer.parseInt(parameter.get("code"));
        MenuDTO menu = menuService.selectByMenuCode(code);

        if (menu !=null){
            printResult.printMenu(menu);
        }else {
            printResult.printErrorMessage("selectOne");
        }
    }

    public void registMenu(Map<String, String> parameter){
        String name = parameter.get("name");
        int price =Integer.parseInt(parameter.get("price"));
        int category =Integer.parseInt(parameter.get("category"));


        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setCategoryCode(category);
        menu.setPrice(price);

        if (menuService.registMenu(menu)){
            printResult.printSuccessMessage("insert");
        }else {
            printResult.printErrorMessage("insert");
        }
    }

    public void modifyMenu(Map<String, String> parameter){
        int code = Integer.parseInt(parameter.get("code"));
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("category"));
        String status =parameter.get("status");
        MenuDTO menu = new MenuDTO(code,name,price,categoryCode,status);

        if (menuService.modifyMenu(menu)){
            printResult.printSuccessMessage("update");
        }else {
            printResult.printErrorMessage("update");
        }
    }

    public void deleteMenu(int code){
        if (menuService.deleteMenu(code)){
            printResult.printSuccessMessage("delete");
        }else {
            printResult.printErrorMessage("delete");
        }
    }
}
