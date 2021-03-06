package com.media.his.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.media.his.entity.StudentKtDO;
import com.media.his.mapper.StudentKtMapper;
import com.media.his.service.StudentKtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentKtServiceImpl extends ServiceImpl<StudentKtMapper, StudentKtDO> implements StudentKtService {

    @Autowired
    private StudentKtMapper studentKtMapper;

    @Override
    public StudentKtDO findStudentKtById(int id) {
        return studentKtMapper.selectById(id);
    }

    @Override
    public List<StudentKtDO> findAllStudentKtDO() {
        return studentKtMapper.selectAllStudentKtDO();
    }

    @Override
    public List<StudentKtDO> findAllStudentKtDOByStartAndEnd(int start, int end) {
        return studentKtMapper.selectAllStudentKtDOByStartAndEnd(start, end);
    }

    @Override
    public int getMaxId() {
        return studentKtMapper.selectMaxId();
    }

    @Override
    public List<StudentKtDO> getStudentByName(List<String> list) {

        return baseMapper.selectStudentKtByName(list);
    }
}
