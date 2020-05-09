package com.fyb.shop.controller;


import com.fyb.shop.common.CommonResult;
import com.fyb.shop.entity.Rights;
import com.fyb.shop.entity.Role;
import com.fyb.shop.entity.User;
import com.fyb.shop.service.IRightService;
import com.fyb.shop.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyb
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/shop/role")
public class RoleController {

    @Autowired
    private HttpSession session;
    private boolean isLogin(HttpSession session){

        User user= (User) session.getAttribute("current_user");
        if(user==null){
            return  false;
        }
        return true;
    }


    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRightService rightService;

    @GetMapping("/roles")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<List<Role>> findAllRoles(){
        CommonResult<List<Role>> result = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            result.setMsg("请登录！");
            return result;
        }
        List<Role> list = roleService.list();
        for (Role role : list
             ) {
            String ids = role.getRightsIds();
            if(!StringUtils.isEmpty(ids)){
                String[] split = ids.split(",");
                int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                List<Integer> idList = Arrays.stream(array).boxed().collect(Collectors.toList());
                ArrayList<Rights> rightsTree = rightService.getAllRightsTree(idList);
                role.setChildren(rightsTree);
            }
        }
        result.setData(list);
        result.setStatus(200);
        result.setMsg("查询所有角色列表成功！");
        return result;
    }



}
