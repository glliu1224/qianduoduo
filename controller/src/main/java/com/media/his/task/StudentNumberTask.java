package com.media.his.task;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.media.his.entity.StudentDO;
import com.media.his.mapper.StudentMapper;
import com.media.his.service.StudentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.RecursiveTask;
@Slf4j
public class StudentNumberTask extends RecursiveTask<List<StudentDO>> {
    int start;
    int end;
    StudentService studentService;
    List<StudentDO> list;


    public StudentNumberTask(int start, int end, StudentService studentService,List<StudentDO> list) {
        this.start = start;
        this.end = end;
        this.studentService = studentService;
        this.list = list;
    }

    @SneakyThrows
    @Override
    protected List<StudentDO> compute() {
        int count = 10000;
        if (end - start > count) {
            int middle = (end + start) / 2;
            StudentNumberTask t1 = new StudentNumberTask(start, end - middle, studentService,list);
            StudentNumberTask t2 = new StudentNumberTask(middle + 1, end, studentService,list);
            t1.fork();
            t2.fork();
            List<StudentDO> join = t1.join();
            List<StudentDO> join1 = t2.join();
            join.addAll(join1);
            return join;
        } else {
            if (start == end) {
                end += 1;
            }
            log.info("开始ID->{},结束ID->{}",start,end);
            List<StudentDO> allPhoneNumber = studentService.findAllPhoneNumber(start,end);
            return allPhoneNumber;
        }
    }
}
