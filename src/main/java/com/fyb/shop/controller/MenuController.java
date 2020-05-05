package com.fyb.shop.controller;


import com.fyb.shop.common.MenuResult;
import com.fyb.shop.entity.User;
import com.fyb.shop.service.IMenuService;
import com.fyb.shop.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyb
 * @since 2020-05-05
 */
@RestController
@RequestMapping("/shop/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/findAllMenu")
    @CrossOrigin(allowCredentials = "true")
    public MenuResult<MenuVo> findAllMenu(HttpSession session){

        User user= (User) session.getAttribute("current_user");
        if(user==null){
            MenuResult<MenuVo> result = new MenuResult<>();
            result.setMsg("请登录！");
            return result;
        }
        MenuResult<MenuVo> result = menuService.findAllMenus();
        result.setStatus(200);
        result.setMsg("查询成功");
        return result;
    }

}
