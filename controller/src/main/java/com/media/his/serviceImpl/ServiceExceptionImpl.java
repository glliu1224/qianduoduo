package com.media.his.serviceImpl;

import com.google.common.base.Preconditions;
import com.media.his.api.IServiceException;
import com.media.his.constant.exception.ExceptionConstants;
import com.media.his.constant.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service("serviceException")
public class ServiceExceptionImpl implements IServiceException {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionImpl.class);
    private static LinkedHashMap<Integer, String> msgMap;

    /**
     * 初始化错误码信息
     */
    @Override
    //@PostConstruct
    public void initServiceExceptionToCache() {
        long now = System.currentTimeMillis();
        LOGGER.info("初始化 ExceptionConstants 错误码信息 开始...");
        LinkedHashMap<Integer, String> tempMsgMap = new LinkedHashMap<Integer, String>();
        ReflectionUtils.doWithFields(ExceptionConstants.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException {
                // 没有 @Error 注解的， 不做处理
                if (field.getAnnotation(ExceptionConstants.Error.class) == null) {
                    LOGGER.warn("错误码: {} 未配置 @Error 注解！", field.getName());
                    return;
                }
                Map<String, Object> row = parseField(field);
                // 重复的code码校验
                if (row.containsKey("code")) {
                    if (tempMsgMap.containsKey(row.get("code"))) {
                        LOGGER.error("错误码重复: {} 的code {} 重复！", field.getName(), row.get("code"));
                    }
                }
                tempMsgMap.put((Integer) row.get("code"), row.get("msg").toString());
            }
        });

        msgMap = tempMsgMap;

        LOGGER.info("初始化 ExceptionConstants 错误码信息完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }

    /**
     * 解析 Field
     *
     * @param field 常量
     */
    private Map<String, Object> parseField(Field field) {
        if (field.isAccessible()) {
            field.setAccessible(true);
        }
        // 类型校验
        if (field.getType() == int.class || field.getType() == Integer.class) {
            // 无异常
        } else {
            throw new RuntimeException("错误码: " + field.getName() + " 类型错误，必须为int/Integer类型数据！");
        }

        Map<String, Object> row = new HashMap<>();
        row.put("name", field.getName()); // 获取常量名称

        ExceptionConstants.Error error = field.getAnnotation(ExceptionConstants.Error.class);
        row.put("module", error.module());
        row.put("code", error.code());
        row.put("msg", error.msg());
        return row;
    }

    /**
     * 返回业务异常
     *
     * @param code
     * @return
     */
    @Override
    public ServiceException createServiceException(int code) {
        Preconditions.checkNotNull(code, "ServiceException code can't NULL!");
        ServiceException serviceException = new ServiceException(code);
        if (null != msgMap && msgMap.containsKey(code)) {
            serviceException.setMessage(msgMap.get(code));
        }
        return serviceException;
    }
}
