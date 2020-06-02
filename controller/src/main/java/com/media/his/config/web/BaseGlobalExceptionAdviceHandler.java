package com.media.his.config.web;

import com.media.his.constant.enums.LanguageEnum;
import com.media.his.constant.exception.ServiceException;
import com.media.his.constant.holder.SystemContextHolder;
import com.media.his.constant.result.Result;
import com.media.his.constant.system.ApplicationConfig;
import com.media.his.util.StringUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *  全局异常处理
 */
public abstract class BaseGlobalExceptionAdviceHandler {
    private Logger LOGGER = LoggerFactory.getLogger(BaseGlobalExceptionAdviceHandler.class);

    /**
     * 处理自定义
     */
    public Result handServiceException(ServiceException e) {
        Result result = new Result(LanguageEnum.LANG_ZH_CN.getLanguageDesc(), e.getCode(), null);
        result.setServiceTime(new Date());
        //开发环境
        String message = e.getMessage();
        if (StringUtil.isEmpty(message)) {
            message = "未知错误码，请联系开发小哥哥";
        }
        result.setMessage(message);
        //remove ThreadLocal
        SystemContextHolder.remove();
        return result;
    }

    /**
     * 处理系统异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handSystemException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOGGER.error(ExceptionUtils.getStackTrace(e));
        Result result = null;
        if (e instanceof NoHandlerFoundException) {
            //处理404
            result = new Result(LanguageEnum.LANG_ZH_CN.getLanguageDesc(), 404, null);
            result.setMessage("访问路径不存在");
            if (ApplicationConfig.DEV) {
                //研发环境，返回堆栈信息
                result.setStack(ExceptionUtils.getStackTrace(e));
            }
        } else {
            //处理500
            result = new Result(LanguageEnum.LANG_ZH_CN.getLanguageDesc(), 500, null);
            result.setMessage("系统异常");
            if (ApplicationConfig.DEV) {
                //研发环境，返回堆栈信息
                result.setStack(ExceptionUtils.getStackTrace(e));
            }
        }
        try{
            result.setServiceTime(new Date());
            addServiceExceptionRecord(request,e);
        }catch (Exception ex){
            LOGGER.error("add server exception error:  {}",ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
    //异常日志处理 存入db中
    protected abstract void addServiceExceptionRecord(HttpServletRequest request, Exception e);
}





















