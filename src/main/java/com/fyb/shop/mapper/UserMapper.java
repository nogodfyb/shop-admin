package com.fyb.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shop.entity.User;
import com.fyb.shop.vo.UserVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fyb
 * @since 2020-05-02
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<UserVo> selectPageVo(Page<UserVo> page);

}
