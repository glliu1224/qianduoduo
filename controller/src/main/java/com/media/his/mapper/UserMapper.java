package com.media.his.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.media.his.entity.PeopleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper  extends BaseMapper<PeopleDO> {

    PeopleDO findUserById(@Param("id") Integer id);
}
