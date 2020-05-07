package com.fyb.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shop.common.CommonPage;
import com.fyb.shop.common.CommonResult;
import com.fyb.shop.dto.UserPageParam;
import com.fyb.shop.entity.User;
import com.fyb.shop.mapper.UserMapper;
import com.fyb.shop.service.IUserService;
import com.fyb.shop.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fyb
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/shop/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    private boolean isLogin(HttpSession session){

        User user= (User) session.getAttribute("current_user");
        if(user==null){
            return  false;
        }
        return true;
    }


    @GetMapping("/all")
    @CrossOrigin
    public List<User> findAll() {
        List<User> list = userService.list();
        return list;
    }

    @PostMapping("/login")
    @CrossOrigin(allowCredentials = "true")
    //@CrossOrigin
    public CommonResult<User> login(HttpSession session, @RequestBody User userpara) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", userpara.getUsername()).eq("password",userpara.getPassword());
        User user = userService.getOne(userQueryWrapper);
        CommonResult<User> result = new CommonResult<>();
        if (user != null) {
            session.setAttribute("current_user", user);
            user.setPassword("");
            result.setMsg("登录成功");
            result.setStatus(200);
            result.setData(user);
            return result;
        }
        result.setMsg("登录失败!");
        return result;
    }

    @GetMapping("/get")
    @CrossOrigin(allowCredentials = "true")
    public User get(HttpSession session) {
        User userinfo = (User) session.getAttribute("current_user");
        return userinfo;
    }

    @GetMapping("/users")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<CommonPage<UserVo>> getUsers(@Valid UserPageParam pageParam, HttpSession session){
        CommonResult<CommonPage<UserVo>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        Page<UserVo> userPage = new Page<>();
        userPage.setCurrent(pageParam.getPageNum());
        userPage.setSize(pageParam.getPageSize());
        IPage<UserVo> userIPage = userMapper.selectPageVo(userPage,pageParam.getQuery());
        CommonPage<UserVo> userCommonPage = CommonPage.restPage(userIPage);
        commonResult.setMsg("获取成功");
        commonResult.setData(userCommonPage);
        commonResult.setStatus(200);
        return commonResult;
    }
    @GetMapping("/users/{id}")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<User> getUserById(HttpSession session, @PathVariable Integer id){
        CommonResult<User> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        User user = userService.getById(id);
        if(user==null){
            commonResult.setMsg("获取用户信息失败!");
            return commonResult;
        }
        commonResult.setMsg("成功获取用户信息！");
        commonResult.setStatus(200);
        user.setPassword("");
        commonResult.setData(user);
        return commonResult;
    }

    @PostMapping("/users")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<CommonPage<UserVo>> addUser(@RequestBody User user, HttpSession session){
        CommonResult<CommonPage<UserVo>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        boolean save = userService.save(user);
        if(!save){
            commonResult.setMsg("保存失败！");
            return commonResult;
        }
        commonResult.setStatus(201);
        commonResult.setMsg("保存用户成功！");
        return commonResult;
    }

    @PutMapping("/users")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<CommonPage<UserVo>> updateUser(@RequestBody User user, HttpSession session){
        CommonResult<CommonPage<UserVo>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        user.setPassword(null);
        boolean update = userService.updateById(user);
        if(!update){
            commonResult.setMsg("修改失败！");
            return commonResult;
        }
        commonResult.setStatus(201);
        commonResult.setMsg("修改用户成功！");
        return commonResult;
    }
    @DeleteMapping("/users/{id}")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<CommonPage<UserVo>> deleteUser(HttpSession session, @PathVariable Integer id){
        CommonResult<CommonPage<UserVo>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        boolean remove = userService.removeById(id);
        if(!remove){
            commonResult.setMsg("删除失败！");
            return commonResult;
        }
        commonResult.setStatus(200);
        commonResult.setMsg("删除用户成功！");
        return commonResult;
    }

    @PutMapping("/users/{userId}/mgState/{mgState}")
    @CrossOrigin(allowCredentials = "true")
    public CommonResult<CommonPage<UserVo>> updateMgState(HttpSession session, @PathVariable Integer userId,
                                                          @PathVariable Boolean mgState){
        CommonResult<CommonPage<UserVo>> commonResult = new CommonResult<>();
        boolean login = isLogin(session);
        if(!login){
            commonResult.setMsg("请登录！");
            return commonResult;
        }
        User user = new User();
        user.setId(userId);
        user.setMgState(mgState);
        boolean update = userService.updateById(user);
        if(update){
            commonResult.setMsg("更新成功");
            commonResult.setStatus(200);
        }
        return commonResult;
    }


}
