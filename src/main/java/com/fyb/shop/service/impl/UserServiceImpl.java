package com.fyb.shop.service.impl;

import com.fyb.shop.entity.User;
import com.fyb.shop.mapper.UserMapper;
import com.fyb.shop.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyb
 * @since 2020-05-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
