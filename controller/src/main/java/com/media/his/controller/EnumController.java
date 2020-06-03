package com.media.his.controller;

import com.media.his.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/system/enum")
public class EnumController {

    @Autowired
    private EnumService enumService;

    @GetMapping("all")
    public Map<String,Object> constans(){
        return enumService.findAllEnum();
    }
}
