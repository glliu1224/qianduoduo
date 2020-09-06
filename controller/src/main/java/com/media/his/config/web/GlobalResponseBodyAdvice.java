package com.media.his.config.web;

import com.media.his.constant.enums.LanguageEnum;
import com.media.his.constant.exception.ExceptionConstants;
import com.media.his.constant.holder.SystemContextHolder;
import com.media.his.constant.result.Result;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Date;

/**
 * 全局响应数据处理
 */
@ControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (obj == null || !(obj instanceof Result)) {
            obj = new Result<Object>(LanguageEnum.LANG_ZH_CN.getLanguageDesc(), ExceptionConstants.SUCCESS, obj);
            ((Result) obj).setMessage("请求成功");
            ((Result) obj).setServiceTime(new Date());
        }
        //remove ThreadLocal
        SystemContextHolder.remove();
        return obj;
    }


}
