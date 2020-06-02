package com.media.his.api;

import com.media.his.entity.PeopleDO;


public interface UserApi {

    Integer save(PeopleDO userVO);

    PeopleDO findUser(Integer id);
}
