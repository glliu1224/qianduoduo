package com.media.his.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.media.his.entity.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<StudentDO> {

    List<StudentDO> findStudentByAge(@Param("age") int age);

    List<StudentDO> findNameByAge(@Param("age") int age);

    List<StudentDO> findAddressByAge(@Param("age") int age);

    List<StudentDO> findPhoneNumberByAge(@Param("age") int age);

    List<String> findPhoneNumber();

    Integer findMaxId();

    List<StudentDO> findAllPhoneNumber(@Param("start") int start, @Param("end") int end);

    List<StudentDO> findStudentDOByName(@Param("list") List<String> list);
}
