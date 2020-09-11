package com.media.his.controller;

import com.media.his.entity.PeopleDO;
import com.media.his.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/insertBatch")
    public Integer insertbatch() {
        List<PeopleDO> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            PeopleDO peopleDO = PeopleDO.builder()
                    .user("user_" + i)
                    .age(20)
                    .build();
            list.add(peopleDO);
        }
        peopleService.insertBatch(list, 50);
        return 1;
    }
}
