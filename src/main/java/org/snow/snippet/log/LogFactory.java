package org.snow.snippet.log;

import org.snow.snippet.log.impl.Log4jLoggerAdpter;
import org.snow.snippet.log.impl.NopLog;

public final class LogFactory {

    private static LogAdapter adapter;

    static { init(); }

    public static Log getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Log getLogger(String className) {
        return adapter.getLogger(className);
    }

    public static void init() {
        adapter = new Log4jLoggerAdpter();
    }

    public static void setAdapter(LogAdapter adapter) {
        LogFactory.adapter = adapter;
    }

    public static LogAdapter NOP_ADAPTER = NopLog.NOP;
}
