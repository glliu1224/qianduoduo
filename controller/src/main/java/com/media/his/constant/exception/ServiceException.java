package com.media.his.constant.exception;


import lombok.Data;

/**
 * 异常返回值
 */
@Data
public class ServiceException extends RuntimeException {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;

    public ServiceException() {
    }

    public ServiceException(Integer code) {
        this.code = code;
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
