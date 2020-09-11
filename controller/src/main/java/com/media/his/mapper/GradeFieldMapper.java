package com.media.his.mapper;

import com.media.his.entity.GradeField;
import java.util.List;

public interface GradeFieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GradeField record);

    GradeField selectByPrimaryKey(Integer id);

    List<GradeField> selectAll();

    int updateByPrimaryKey(GradeField record);
}