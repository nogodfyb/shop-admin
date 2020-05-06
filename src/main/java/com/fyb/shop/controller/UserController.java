package com.fyb.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shop.common.CommonResult;
import com.fyb.shop.entity.User;
import com.fyb.shop.mapper.UserMapper;
import com.fyb.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/test")
    public IPage<User> test(){
        IPage<User> userIPage = userMapper.selectPageVo(new Page<User>());
        return userIPage;
    }


}
