package com.media.his.controller;

import com.media.his.entity.StudentDO;
import com.media.his.pojoVO.StudentVO;
import com.media.his.service.StudentService;
import com.media.his.serviceImpl.StudentServiceImpl;
import com.media.his.task.StudentTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,10, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(5));

    @GetMapping("/getStudentByAge")
    public List<StudentVO> getStudentByAge(@RequestParam("age")int age) throws ExecutionException, InterruptedException {
        Future<List<StudentDO>> findAddressByAge = executor.submit(new StudentTask(age, studentService, "findAddressByAge", StudentServiceImpl.class));
        List<StudentDO> list = findAddressByAge.get();
        for (StudentDO studentDO : list) {
            log.info("查询到的数据->{}",studentDO);
        }
        return null;
    }

    @GetMapping("/getPhoneNumber")
    public List<String> getPhoneNumber() {
        long start = System.currentTimeMillis();
        List<String> phoneNumbers = studentService.findPhoneNumber();
        log.info("查询执行了->{}",(System.currentTimeMillis() - start) + "毫秒");
        return phoneNumbers;
    }

}
