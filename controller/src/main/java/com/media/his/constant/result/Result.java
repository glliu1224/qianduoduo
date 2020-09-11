package com.media.his.constant.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Rest接口返回信息
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 语言
     */
    private String language;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 服务器时间
     */
    private Date serviceTime;
    /**
     * 开发阶段，返回堆栈信息
     */
    private Object stack;

    public Result(String language, Integer code, T date) {
        this.language = language;
        this.code = code;
        this.data = date;
    }

}
