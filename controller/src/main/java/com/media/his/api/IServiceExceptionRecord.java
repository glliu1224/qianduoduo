package com.media.his.api;

import java.util.Date;

public interface IServiceExceptionRecord {
    /**
     * 存储异常信息
     * @param stackTrace
     * @param requestURI
     * @param method
     * @param buildQueryString
     * @param ua
     * @param ip
     * @param date
     */
    void addServiceExceptionRecord(String stackTrace, String requestURI, String method, String buildQueryString, String ua, String ip, Date date);
}
