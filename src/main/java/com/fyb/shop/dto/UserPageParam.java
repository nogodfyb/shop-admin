package com.fyb.shop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserPageParam {

    @NotNull(message = "pageNum不能为空")
    private Integer pageNum;
    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    @NotNull(message = "query不能为空")
    private String query;

}
