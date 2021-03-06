package com.media.his.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.media.his.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
    int insertBatch(@Param("list") List<RoleDO> list);
}
