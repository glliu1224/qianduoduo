package com.media.his.mapper;

import com.media.his.entity.GradeFieldShow;

import java.util.List;

public interface GradeFieldShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GradeFieldShow record);

    GradeFieldShow selectByPrimaryKey(Integer id);

    List<GradeFieldShow> selectAll();

    int updateByPrimaryKey(GradeFieldShow record);
}