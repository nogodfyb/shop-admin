package com.fyb.shop.controller;


import com.fyb.shop.common.CommonResult;
import com.fyb.shop.entity.Rights;
import com.fyb.shop.entity.User;
import com.fyb.shop.service.IRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author fyb
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/shop/right")
public class RightController {

    private boolean isLogin(HttpSession session){

        User user= (User) session.getAttribute("current_user");
        if(user==null){
            return  false;
        }
        return true;
    }

    @Autowired
    private HttpSession session;

    @Autowired
    private IRightService rightService;


    @GetMapping("/list")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<List<Rights>> listAll(){
        CommonResult<List<Rights>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        List<Rights> rightsList = rightService.list();
        if(rightsList ==null){
            commonResult.setMsg("查询权限列表失败");
            return  commonResult;
        }
        commonResult.setStatus(200);
        commonResult.setData(rightsList);
        commonResult.setMsg("查询权限列表成功!");
        return commonResult;
    }

    @GetMapping("/tree")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<List<Rights>> getAllRightsTree(){
        CommonResult<List<Rights>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        ArrayList<Rights> allRightsTree = rightService.getAllRightsTree(null);
        commonResult.setMsg("查询成功权限树！");
        commonResult.setStatus(200);
        commonResult.setData(allRightsTree);
        return commonResult;
    }


}
