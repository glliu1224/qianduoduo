package com.media.his.mapper;

import com.media.his.entity.RoleDO;
import java.util.List;

public interface RoleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleDO record);

    RoleDO selectByPrimaryKey(Integer id);

    List<RoleDO> selectAll();

    int updateByPrimaryKey(RoleDO record);
}