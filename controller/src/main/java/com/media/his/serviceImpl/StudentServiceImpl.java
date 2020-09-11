package com.media.his.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.media.his.mapper.StudentMapper;
import com.media.his.entity.StudentDO;
import com.media.his.pojoVO.StudentVO;
import com.media.his.service.StudentService;
import com.media.his.task.StudentNumberTask;
import com.media.his.util.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO> implements StudentService {

    public List<StudentVO> findStudentByAge(int age) {
        long start = System.currentTimeMillis();
        List<StudentDO> studentByAge = baseMapper.findStudentByAge(age);
        long end = System.currentTimeMillis();
        log.info("查询数据耗时:{}", (end - start));
        long l = System.currentTimeMillis();
        List<StudentVO> convert = EntityUtil.convert(studentByAge, StudentVO.class);
        long l1 = System.currentTimeMillis();
        log.info("数据进行转换耗时:{}", l1 - l);
        return convert;
    }

    @Override
    public List<StudentDO> findNameByAge(int age) {
        return baseMapper.findNameByAge(age);
    }

    @Override
    public List<StudentDO> findAddressByAge(int age) {
        return baseMapper.findAddressByAge(age);
    }

    @Override
    public List<StudentDO> findPhoneNumberByAge(int age) {
        return baseMapper.findPhoneNumberByAge(age);
    }

    @Override
    public List<String> findPhoneNumber() {
        return baseMapper.findPhoneNumber();
    }

    @Override
    public Integer findMaxId() {
        return baseMapper.findMaxId();
    }

    @Override
    public List<StudentDO> findAllPhoneNumber(int start, int end) {
        return baseMapper.findAllPhoneNumber(start, end);
    }

    @Override
    public List<StudentDO> findStudentDOByName(List<String> list) {
        return baseMapper.findStudentDOByName(list);
    }
}
