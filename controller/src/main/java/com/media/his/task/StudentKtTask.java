package com.media.his.task;

import com.media.his.entity.StudentKtDO;
import com.media.his.service.StudentKtService;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class StudentKtTask extends RecursiveTask<List<StudentKtDO>> {
    private int start;
    private int end;
    private StudentKtService studentKtService;

    public StudentKtTask(int start, int end, StudentKtService studentKtService) {
        this.start = start;
        this.end = end;
        this.studentKtService = studentKtService;
    }

    @SneakyThrows
    @Override
    protected List<StudentKtDO> compute() {
        int count = 10000;
        if (end - start < count) {
            List<StudentKtDO> list = studentKtService.findAllStudentKtDOByStartAndEnd(start, end);
            return list;
        } else {
            int middle = (end + start) / 2;
            StudentKtTask t1 = new StudentKtTask(start, middle, studentKtService);
            StudentKtTask t2 = new StudentKtTask(middle + 1, end, studentKtService);
            ForkJoinTask<List<StudentKtDO>> fork = t1.fork();
            ForkJoinTask<List<StudentKtDO>> fork1 = t2.fork();
            List<StudentKtDO> join = fork.join();
            List<StudentKtDO> join1 = fork1.join();
            join.addAll(join1);
            return join;
        }
    }
}
