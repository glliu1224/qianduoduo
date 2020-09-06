package com.media.his.api;

import com.baomidou.mybatisplus.service.IService;
import com.media.his.entity.PeopleDO;

import java.util.List;


public interface UserApi{

    Integer save(PeopleDO userVO);

    PeopleDO findUser(Integer id);

    boolean insertBatch();
}
