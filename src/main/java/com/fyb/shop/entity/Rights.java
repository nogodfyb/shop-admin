package com.fyb.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author fyb
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Rights implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 控制器
     */
    private String controller;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 权限等级
     */
    private String level;

    //不是数据库字段，不需要映射
    @TableField(exist=false)
    private List<Rights> children;


}
