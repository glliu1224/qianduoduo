package com.media.his.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.media.his.config.common.FieldMeta;
import lombok.Data;

import java.util.Date;

@Data
@TableName("service_exception_record")
public class ServiceExceptionRecordDO {
    /**
     * 主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    @FieldMeta(name = "自增id")
    private Integer id;
    /**
     * 异常名
     */
    @FieldMeta(name = "异常名")
    private String exception;
    /**
     * 请求的url
     */
    @FieldMeta(name = "请求的url")
    private String requestUrl;
    /**
     * 请求方式
     */
    @FieldMeta(name = "请求方式")
    private String requestMethod;
    /**
     * 请求参数
     */
    @FieldMeta(name = "请求参数")
    private String requestParams;
    /**
     * 请求工具路径
     */
    @FieldMeta(name = "请求工具路径")
    private String ua;
    /**
     * 请求异常ip
     */
    @FieldMeta(name = "请求异常ip")
    private String ip;
    /**
     * 异常时间
     */
    @FieldMeta(name = "异常时间")
    private Date createTime;


}
