package com.fyb.shop.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {


    private Integer id;

    private List<MenuVo> children;

    private String authName;

    private String path;

    private Integer order;


}
