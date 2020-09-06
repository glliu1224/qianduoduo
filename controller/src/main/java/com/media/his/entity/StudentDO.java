package com.media.his.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Table;

@Data
@TableName("student")
public class StudentDO {
    @TableId(value = "id")
    private int id;
    private String name;
    private String address;
    private int age;
    private String phoneNumber;

    public StudentDO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
