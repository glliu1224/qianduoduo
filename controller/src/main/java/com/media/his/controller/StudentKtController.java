package com.media.his.controller;

import com.media.his.entity.StudentKtDO;
import com.media.his.service.StudentKtService;
import com.media.his.task.StudentKtTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
@RestController
@RequestMapping("/studentKt")
@Slf4j
public class StudentKtController {

    @Autowired
    private StudentKtService studentKtService;

    @GetMapping("/getStudentKtById")
    public StudentKtDO getStudentKtById(@Param("id") int id) {
        return studentKtService.findStudentKtById(id);
    }

    @GetMapping("/getAllStudentKt")
    public List<StudentKtDO> getAllStudentKt() {
        long start = System.currentTimeMillis();
        List<StudentKtDO> allStudentKtDO = studentKtService.findAllStudentKtDO();
        log.info("没使用线程池查询耗时--->{}",(System.currentTimeMillis() - start)+"毫秒");
        return allStudentKtDO;
    }

    @GetMapping("/getAllStudentKtByForkJoinPool")
    public List<StudentKtDO> getAllStudentKtByForkJoinPool() throws ExecutionException, InterruptedException {
        long str = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(5, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
        long start = System.currentTimeMillis();
        int maxId = studentKtService.getMaxId();
        log.info("获取最大的ID耗时--->{}", (System.currentTimeMillis() - start));
        StudentKtTask studentKtTask = new StudentKtTask(0,maxId,studentKtService);
        ForkJoinTask<List<StudentKtDO>> submit = pool.submit(studentKtTask);
        List<StudentKtDO> list = submit.get();
        pool.shutdown();
        log.info("获取集合数据耗时--->{}", System.currentTimeMillis() - str);
        return list;
    }
}
