package com.media.his.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.media.his.api.IServiceExceptionRecord;
import com.media.his.entity.ServiceExceptionRecordDO;
import com.media.his.mapper.ExceptionRecordMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceExceptionRecordImpl extends ServiceImpl<ExceptionRecordMapper, ServiceExceptionRecordDO> implements IServiceExceptionRecord {

    /**
     * 存储异常信息
     *
     * @param stackTrace
     * @param requestURI
     * @param method
     * @param buildQueryString
     * @param ua
     * @param ip
     * @param date
     */
    @Override
    public void addServiceExceptionRecord(String stackTrace, String requestURI, String method, String buildQueryString, String ua, String ip, Date date) {
        ServiceExceptionRecordDO serviceExceptionRecordDO = new ServiceExceptionRecordDO();
        serviceExceptionRecordDO.setException(stackTrace);
        serviceExceptionRecordDO.setRequestUrl(requestURI);
        serviceExceptionRecordDO.setRequestMethod(method);
        serviceExceptionRecordDO.setRequestParams(buildQueryString);
        serviceExceptionRecordDO.setUa(ua);
        serviceExceptionRecordDO.setIp(ip);
        serviceExceptionRecordDO.setCreateTime(date);
        baseMapper.insert(serviceExceptionRecordDO);
    }
}
