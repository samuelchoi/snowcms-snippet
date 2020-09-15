package org.snow.snippet.log;

import org.snow.snippet.log.impl.Log4jLoggerAdpter;
import org.snow.snippet.log.impl.NopLog;

/**
 * Log 创建工厂
 */
public final class LogFactory {

    static { init(); }

    private static LogAdapter adapter;

    /* 获取 logger  */
    public static Log getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Log getLogger(String className) {
        return adapter.getLogger(className);
    }

    /* 默认实现 log4j  */
    public static void init() {
        try {
            adapter = new Log4jLoggerAdpter();
        } catch (Exception ex) {
            // no to doing
        } finally {
            adapter = NopLog.NOP;
        }
    }

    /* 设置实现类 log */
    public static void setAdapter(LogAdapter adapter) {
        LogFactory.adapter = adapter;
    }
}
