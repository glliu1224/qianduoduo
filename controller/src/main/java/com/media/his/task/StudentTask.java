package com.media.his.task;

import com.media.his.entity.StudentDO;
import com.media.his.service.StudentService;
import com.media.his.serviceImpl.StudentServiceImpl;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;

@Data
public class StudentTask implements Callable<List<StudentDO>> {
    int age;
    StudentService service;
    String methodName;
    Class<StudentServiceImpl> clazz;

    public StudentTask(int age, StudentService service, String methodName, Class<StudentServiceImpl> clazz) {
        this.age = age;
        this.service = service;
        this.methodName = methodName;
        this.clazz = clazz;
    }

    @Override
    public List<StudentDO> call() throws Exception {
        Method method = clazz.getMethod(methodName, Integer.class);
        List<StudentDO> list = (List<StudentDO>) method.invoke(service, age);
        return list;
    }
}
