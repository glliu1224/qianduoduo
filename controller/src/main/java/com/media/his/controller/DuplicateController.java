package com.media.his.controller;

import com.media.his.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ON DUPLICATE 批量插入数据测试
 */
@RestController
@RequestMapping("/duplicate")
public class DuplicateController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/insertBatch")
    public void insertBatch() {
        roleService.roleInsertBatch();
    }

}
