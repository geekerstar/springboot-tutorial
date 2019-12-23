package com.geekerstar.exception.code;

/**
 * @author geekerstar
 * date: 2019/12/22 09:04
 * description: 公共异常定义，公用的异常定义在这里
 */
public class CommonException {

    public static final BusinessException TEST_EXCEPTION = new BusinessException("C00001", "这个是示例，抛出具体信息写在这里");

    public static final BusinessException PARAMS_IS_NULL = new BusinessException("C00001", "[%s]不能为空!");
    public static final BusinessException INFO_IS_NULL = new BusinessException("C60004", "信息不存在!");
}
