package com.media.his.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("student_kt")
public class StudentKtDO {

    @TableId(value = "id")
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private Date createTime;
    private Date updateTime;
}
