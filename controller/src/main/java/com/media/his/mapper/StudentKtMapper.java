package com.media.his.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.media.his.entity.StudentKtDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentKtMapper extends BaseMapper<StudentKtDO> {

    StudentKtDO findById(@Param("id") int id);
}
