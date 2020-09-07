package com.media.his.controller;

import com.media.his.entity.StudentKtDO;
import com.media.his.service.StudentKtService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studentKt")
public class StudentKtController {

    @Autowired
    private StudentKtService studentKtService;

    @GetMapping("/getStudentKtById")
    public StudentKtDO getStudentKtById(@Param("id") int id) {
        return studentKtService.findStudentKtById(id);
    }

}
