package com.media.his.service;

import com.baomidou.mybatisplus.service.IService;
import com.media.his.entity.StudentDO;
import com.media.his.pojoVO.StudentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface StudentService extends IService<StudentDO>{

    List<StudentVO> findStudentByAge(int age);

    List<StudentDO> findNameByAge(@Param("age") int age);

    List<StudentDO> findAddressByAge(int age);

    List<StudentDO> findPhoneNumberByAge(@Param("age") int age);

    List<String> findPhoneNumber();

    Integer findMaxId();

    List<StudentDO> findAllPhoneNumber(@Param("start") int start,@Param("end") int end) throws ExecutionException, InterruptedException;
}
