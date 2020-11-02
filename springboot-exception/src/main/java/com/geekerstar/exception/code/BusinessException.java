package com.geekerstar.exception.code;

import lombok.Getter;

/**
 * @author geekerstar
 * date: 2019/12/22 08:59
 * description: 统一异常定义，抛出业务异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 6237152976831943115L;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    public BusinessException(String code, String message) {
        super("[code=" + code + ", msg=" + message + "]");
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code, String msgFormat, Object... args) {
        super("[code=" + code + ", msg=" + String.format(msgFormat, args) + "]");
        this.code = code;
        this.message = String.format(msgFormat, args);
    }

    public BusinessException(BusinessException e, Object... args) {
        super("[code=" + e.getCode() + ", msg=" + String.format(e.getMessage(), args) + "]");
        this.code = e.getCode();
        this.message = String.format(e.getMessage(), args);
    }
}
