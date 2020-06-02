package com.media.his.constant.exception;

import java.lang.annotation.*;

public class ExceptionConstants {
    //---------------------------公共错误码[-1~999][start]-----------------------------------
    //---------------------------公共错误码[-1~999][end]-------------------------------------
    @Error(module = Error.MODULE_SYSTEM, code = -1, msg = "失败")
    public static final Integer FAILURE = -1;//失败
    @Error(module = Error.MODULE_SYSTEM, code = 0, msg = "success")
    public static final Integer SUCCESS = 0;//成功
    @Error(module = Error.MODULE_SYSTEM, code = 100, msg = "参数不能为空")
    public static final Integer PARAMS_NOT_NULL = 100;//参数不能为空
    @Error(module = Error.MODULE_SYSTEM, code = 101, msg = "参数不正确")
    public static final Integer PARAMS_ERROR = 101;//参数不正确
    @Error(module = Error.MODULE_SYSTEM, code = 102, msg = "数据不存在")
    public static final Integer DATA_NOT_FOUND = 102;//数据不存在
    @Error(module = Error.MODULE_SYSTEM, code = 103, msg = "实体已存在")
    public static final Integer PO_EXISTS = 103;//实体已存在
    /**
     * 请求未授权
     */
    @Error(module = Error.MODULE_SYSTEM, code = 401, msg = "请求未授权")
    public static final Integer ACCESS_UNATHORIZED = 401;
    /**
     * 参数异常
     */
    @Error(module = Error.MODULE_SYSTEM, code = 402, msg = "token 失效！")
    public final static Integer TOKEN_INVALID = 402;
    /**
     * 拒绝访问
     */
    @Error(module = Error.MODULE_SYSTEM, code = 403, msg = "拒绝访问")
    public static final Integer USER_ACCESS_DENIED = 403;
    /**
     * 参数异常
     */
    @Error(module = Error.MODULE_SYSTEM, code = 999, msg = "参数异常")
    public final static Integer PARAMETER_EXCEPTION = 999;

    /**
     * 错误信息注解
     */
    @Documented
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Error {

        //---------------------模块 begin----------------------
        /**
         * 模块 - 系统
         */
        String MODULE_SYSTEM = "SYSTEM";

        /**
         * 模块 - 权限
         */
        String MODULE_AUTH = "AUTH";

        /**
         * 模块 - 用户
         */
        String MODULE_USER = "USER";

        /**
         * 模块 - 短信模板
         */
        String MODULE_SMS_TEMPLATE = "TEMPLATE";

        //-----------------------模块 end--------------------

        /**
         * @return 模块
         */
        String module();

        /**
         * @return 错误码
         */
        int code();

        /**
         * @return 信息
         */
        String msg();

    }
}
