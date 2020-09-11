package com.media.his.constant.holder;

/**
 * 系统线程处理
 */
public class SystemContextHolder {
    /**
     * 创建共享线程
     */
    private static ThreadLocal<String> currentSystemThread = new ThreadLocal();

    /**
     * 设置当前系统
     */
    public static void put(String name) {
        currentSystemThread.set(name);
    }

    /**
     * 获取请求线程变量
     */
    public static String getCurrentSystem() {
        return currentSystemThread.get();
    }

    /**
     * 清空请求线程变量
     */
    public static void removeCurrentSystem() {
        currentSystemThread.remove();
    }

    //remove ThreadLocal
    public static void remove() {
        try {
            removeCurrentSystem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
