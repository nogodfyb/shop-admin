package com.fyb.shop.common;

import lombok.Data;

@Data
public class CommonResult<T> {
    //前端提示使用
    private String msg;
    //状态码
    private int status;
    //前端使用数据
    private T data;

}
