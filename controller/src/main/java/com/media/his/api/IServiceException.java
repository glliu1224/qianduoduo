package com.media.his.api;

import com.media.his.constant.exception.ServiceException;

public interface IServiceException {
    /**
     * 初始化错误信息置缓存中
     */
    void initServiceExceptionToCache();

    /**
     * 返回业务异常
     */
    ServiceException createServiceException(int code);
}
