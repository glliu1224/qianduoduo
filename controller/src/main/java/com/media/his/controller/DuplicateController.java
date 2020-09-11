package com.media.his.controller;

import com.media.his.entity.ContractDO;
import com.media.his.entity.ProjectDO;
import com.media.his.entity.RoleDO;
import com.media.his.service.RoleService;
import com.media.his.util.EntityConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * ON DUPLICATE 批量插入数据测试
 */
@RestController
@RequestMapping("/duplicate")
public class DuplicateController {

    @Autowired
    private RoleService roleService;

    /**
     * 利用mysql特性实现ON DUPLICATE批量插入
     */
    @PostMapping("/insertBatch")
    public void insertBatch() {
        roleService.roleInsertBatch();
    }

    /**
     * 实体类转换测试
     */
    @GetMapping("/getContract")
    public ContractDO getContractDO() {
        ProjectDO projectDO = new ProjectDO();
        projectDO.setId(1);
        projectDO.setProjectName("测试项目");
        projectDO.setProjectDesc("测试项目描述");
        projectDO.setProjectType(1);
        projectDO.setCreateTime(new Date());
        ContractDO contractDO = EntityConvertUtil.INSTANCE.projectToContract(projectDO);
        return contractDO;
    }

    @GetMapping("/lambda")
    public List<RoleDO> testLambda() {
        return roleService.testLambda();
    }

}
