package com.media.his.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.media.his.config.common.FieldMeta;
import lombok.Data;


@Data
@TableName("people")
public class PeopleDO {

    @TableId(value = "id", type = IdType.AUTO)
    @FieldMeta(name = "自增id")
    private Integer id;
    @FieldMeta(name = "姓名")
    private String user;
    @FieldMeta(name = "年龄")
    private Integer age;

}
