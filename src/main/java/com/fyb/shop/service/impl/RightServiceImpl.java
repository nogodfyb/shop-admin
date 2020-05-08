package com.fyb.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyb.shop.entity.Rights;
import com.fyb.shop.mapper.RightMapper;
import com.fyb.shop.service.IRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author fyb
 * @since 2020-05-08
 */
@Service
public class RightServiceImpl extends ServiceImpl<RightMapper, Rights> implements IRightService {

    @Autowired
    private RightMapper rightMapper;


    @Override
    public ArrayList<Rights> getAllRightsTree(){
        //原始数据
        List<Rights> rootList = rightMapper.selectList(null);
        //最后的结果
        ArrayList<Rights> categoryArrayList = new ArrayList<>();
        //先找到所有的一级菜单，parentid为0
        for (int i = 0; i <rootList .size() ; i++) {
            if(rootList .get(i).getPid()==0){
                categoryArrayList.add(rootList.get(i));
            }
        }
        //为一级菜单设置子菜单
        for (Rights category: categoryArrayList
        ) {
            category.setChildren(getChildren(category.getId(),rootList));
        }
        return categoryArrayList;
    }

    /**
     * 递归查找子菜单
     * @param id 当前菜单id
     * @param rootList 要查找的列表
     * @return
     */
    private List<Rights> getChildren(Integer id, List<Rights> rootList) {
        //子菜单
        ArrayList<Rights> childList = new ArrayList<>();
        for (Rights category: rootList
        ) {
            //遍历所有节点，将父菜单id与传过来的的id比较
            if(category.getPid()!=0){
                if(category.getPid().equals(id)){
                    childList.add(category);
                }
            }
        }
        //把子菜单的子菜单再循环一遍
        for (Rights category:childList
        ) {
            //递归
            category.setChildren(getChildren(category.getId(),rootList));
        }
        //递归退出条件
        if(childList.size()==0){
            return null;
        }
        return childList;
    }



}
