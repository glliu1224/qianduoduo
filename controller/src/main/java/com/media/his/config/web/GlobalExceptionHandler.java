package com.media.his.config.web;

import com.media.his.api.IServiceExceptionRecord;
import com.media.his.util.HttpUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 异常DB处理
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionAdviceHandler {
    @Autowired
    private IServiceExceptionRecord serviceExceptionRecord;

    @Override
    protected void addServiceExceptionRecord(HttpServletRequest request, Exception e) {
        serviceExceptionRecord.addServiceExceptionRecord(
                ExceptionUtils.getStackTrace(e),
                request.getRequestURI(),
                request.getMethod(),
                HttpUtil.buildQueryString(request),
                HttpUtil.getUA(request),
                HttpUtil.getIP(request),
                new Date()
        );
    }
}
