package com.fyb.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyb.shop.common.MenuResult;
import com.fyb.shop.entity.Menu;
import com.fyb.shop.mapper.MenuMapper;
import com.fyb.shop.service.IMenuService;
import com.fyb.shop.vo.MenuVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyb
 * @since 2020-05-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public MenuResult<MenuVo> findAllMenus(){
        MenuResult<MenuVo> menuVoMenuResult = new MenuResult<>();
        ArrayList<MenuVo> menuVos = new ArrayList<>();
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("parent_id",0);
        List<Menu> parentList = list(menuQueryWrapper);
        for (Menu menu:parentList
             ) {
            MenuVo menuVo = new MenuVo();
            menuVo.setId(menu.getId());
            menuVo.setAuthName(menu.getAuthName());
            menuVo.setOrder(menu.getSortOrder());
            menuVo.setPath(menu.getPath());
            ArrayList<MenuVo> childMenuVos = new ArrayList<>();
            menuVo.setChildren(childMenuVos);
            QueryWrapper<Menu> menuQueryWrapperChildren = new QueryWrapper<>();
            menuQueryWrapperChildren.eq("parent_id",menu.getId());
            List<Menu> childrenList = list(menuQueryWrapperChildren);
            for (Menu childMenu:childrenList
                 ) {
                MenuVo childMenuVo = new MenuVo();
                childMenuVo.setId(childMenu.getId());
                childMenuVo.setAuthName(childMenu.getAuthName());
                childMenuVo.setOrder(childMenu.getSortOrder());
                childMenuVo.setPath(childMenu.getPath());
                childMenuVos.add(childMenuVo);
            }
            menuVos.add(menuVo);
        }
        menuVoMenuResult.setData(menuVos);

        return menuVoMenuResult;
    }

}
