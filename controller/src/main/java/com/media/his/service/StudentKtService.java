package com.media.his.service;

import com.baomidou.mybatisplus.service.IService;
import com.media.his.entity.StudentKtDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentKtService extends IService<StudentKtDO> {
    StudentKtDO findStudentKtById(int id);

    List<StudentKtDO> findAllStudentKtDO();

    List<StudentKtDO> findAllStudentKtDOByStartAndEnd(int start, int end);

    int getMaxId();
}
