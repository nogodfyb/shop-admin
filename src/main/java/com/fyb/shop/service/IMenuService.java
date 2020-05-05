package com.fyb.shop.service;

import com.fyb.shop.common.MenuResult;
import com.fyb.shop.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fyb.shop.vo.MenuVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fyb
 * @since 2020-05-05
 */
public interface IMenuService extends IService<Menu> {

    MenuResult<MenuVo> findAllMenus();
}
