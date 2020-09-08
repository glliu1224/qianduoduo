package com.media.his.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.media.his.entity.RoleDO;
import com.media.his.mapper.RoleMapper;
import com.media.his.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public void roleInsertBatch() {
        RoleDO roleDO = new RoleDO();
        roleDO.setId(10);
        roleDO.setRoleName("第一个");
        roleDO.setCreateTime(new Date());
        roleDO.setUpdateTime(new Date());
        RoleDO roleDO1 = new RoleDO();
        roleDO.setId(11);
        roleDO.setRoleName("第二个");
        roleDO.setCreateTime(new Date());
        roleDO.setUpdateTime(new Date());
        List<RoleDO> list = new ArrayList<>();
        list.add(roleDO);
        list.add(roleDO1);
        roleMapper.insertBatch(list);
        log.info("插入成功");
    }


}
