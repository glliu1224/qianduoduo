package com.media.his.service;

import com.baomidou.mybatisplus.service.IService;
import com.media.his.entity.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends IService<RoleDO> {
    void roleInsertBatch();

    List<RoleDO> testLambda();
}
