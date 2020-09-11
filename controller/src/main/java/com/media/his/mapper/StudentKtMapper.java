package com.media.his.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.media.his.entity.StudentKtDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentKtMapper extends BaseMapper<StudentKtDO> {

    StudentKtDO findById(@Param("id") int id);

    List<StudentKtDO> selectAllStudentKtDO();

    List<StudentKtDO> selectAllStudentKtDOByStartAndEnd(@Param("start") int start, @Param("end") int end);

    int selectMaxId();

    List<StudentKtDO> selectStudentKtByName(@Param("list") List<String> list);
}
