package com.media.his.controller;

import com.media.his.entity.PeopleDO;
import com.media.his.pojoVO.PeopleVO;
import com.media.his.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test() {
        return ("程序逻辑返回");
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello springBoot";
    }

    @PostMapping("/save")
    public Integer saveUser(@RequestBody PeopleDO user){
        return userService.save(user);
    }

    @GetMapping("/getUser")
    public PeopleVO findUser(@RequestParam("id") Integer id){
        //int i = 1/0;
        return userService.findUser(id);
    }

    @PostMapping("/insertBatch")
    public Integer insertBatch() {
        return userService.insertBatch();
    }


}
