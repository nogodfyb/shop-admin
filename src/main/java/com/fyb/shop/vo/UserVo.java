package com.fyb.shop.vo;

import lombok.Data;

@Data
public class UserVo {
    private Integer id;
    private String username;
    private String mobile;
    private Integer type;
    private Boolean mgState;
}
