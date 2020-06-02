package com.media.his.service;

import com.media.his.api.UserApi;
import com.media.his.entity.PeopleDO;
import com.media.his.pojoVO.PeopleVO;
import com.media.his.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserApi userApi;

    /**
     * 保存user
     * @param user
     */
    public Integer save(PeopleDO user) {
        return userApi.save(user);
    }

    /**
     * 查找user
     * @param id
     */
    public PeopleVO findUser(Integer id) {
        PeopleDO peopleDO = userApi.findUser(id);
        PeopleVO peopleVO = EntityUtil.convert(peopleDO, PeopleVO.class);
        return peopleVO;
    }
}
