package com.media.his.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.media.his.entity.PeopleDO;
import com.media.his.mapper.PeopleMapper;
import com.media.his.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper,PeopleDO> implements PeopleService{

    @Override
    public boolean insertBatch(List<PeopleDO> entityList) {
        return super.insertBatch(entityList);
    }


}
