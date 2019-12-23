package com.geekerstar.exception.code;

/**
 * @author geekerstar
 * date: 2019/12/22 09:04
 * description: 系统异常定义，一般定义由于系统内部问题抛出的异常
 */
public class SystemException {

    public static final BusinessException SYS_ERROR = new BusinessException("S00001", "服务器繁忙,请稍后重试!");

}
