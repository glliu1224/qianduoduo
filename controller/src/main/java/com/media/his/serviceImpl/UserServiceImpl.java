package com.media.his.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.media.his.api.UserApi;
import com.media.his.entity.PeopleDO;
import com.media.his.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, PeopleDO> implements UserApi {

    @Override
    public Integer save(PeopleDO userVO) {
        return baseMapper.insert(userVO);
    }

    @Override
    public PeopleDO findUser(Integer id) {
        return baseMapper.findUserById(id);
    }
}
