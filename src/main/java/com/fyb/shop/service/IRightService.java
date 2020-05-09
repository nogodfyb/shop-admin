package com.fyb.shop.service;

import com.fyb.shop.entity.Rights;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author fyb
 * @since 2020-05-08
 */
public interface IRightService extends IService<Rights> {


    ArrayList<Rights> getAllRightsTree(List<Integer> idlist);
}
