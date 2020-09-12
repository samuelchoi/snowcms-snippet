package org.snow.snippet.log;

import org.snow.snippet.log.impl.NopLog;

public final class LogFactory {

    private static LogAdapter adapter;

    static { init(); }

    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String className) {
        return adapter.getLogger(className);
    }

    /**
     * 初始化 NutLog,检查全部 Log 的可用性,选择可用的 Log 适配器
     * 加载本类时,该方法已经在静态构造函数中调用,用户无需主动调用.
     * 除非迫不得已,请不要调用本方法
     */
    public static void init() {
        try {
            String packageName = LogFactory.class.getPackage().getName() + ".impl.";
            adapter = new SimplePluginManager<LogAdapter>(
                    packageName + "CustomLogAdapter",
                    packageName + "Slf4jLogAdapter",
                    packageName + "Log4jLogAdapter",
                    packageName + "SystemLogAdapter").get();
        }
        catch (Throwable e) {
            try {
                Log4jLogAdapter tmp = new Log4jLogAdapter();
                if (tmp.canWork())
                    adapter = tmp;
                else
                    adapter = new SystemLogAdapter();
            } catch (Throwable _e) {
                adapter = new SystemLogAdapter();
            }
        }
    }

    /**
     * 开放自定义设置 LogAdapter ,注意不能设置为 null 如果你打算完全禁用 Nutz 的 log ,可以设置为 NOP_ADAPTER
     * @param adapter 你所偏好的LogAdapter
     */
    public static void setAdapter(LogAdapter adapter) {
        LogFactory.adapter = adapter;
    }

    /**
     * 什么都不做的适配器,无任何输出,某些人就想完全禁用掉 NutzLog ,就可以用上它了
     */
    public static LogAdapter NOP_ADAPTER = NopLog.NOP;
}
