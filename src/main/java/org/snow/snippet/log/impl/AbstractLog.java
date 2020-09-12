package org.snow.snippet.log.impl;

import org.snow.snippet.log.Log;
import java.util.Arrays;

public abstract class AbstractLog implements Log {

    protected boolean isFatalEnabled = true;
    protected boolean isErrorEnabled = true;
    protected boolean isWarnEnabled = true;
    protected boolean isInfoEnabled = false;
    protected boolean isDebugEnabled = false;
    protected boolean isTraceEnabled = false;

    protected static final int LEVEL_FATAL = 50;
    protected static final int LEVEL_ERROR = 40;
    protected static final int LEVEL_WARN = 30;
    protected static final int LEVEL_INFO = 20;
    protected static final int LEVEL_DEBUG = 10;
    protected static final int LEVEL_TRACE = 0;

    private static final LogEntity LOGINFO_ERROR = new LogEntity();
    private static final LogEntity LOGINFO_NULL = new LogEntity();

    static {
        LOGINFO_ERROR.message = "Log Fail";
        LOGINFO_NULL.message = "null";
    }

    public static int level(String level) {

        if (null != level) {
            if ("F".equals(level) || "fatal".equals(level))
                return LEVEL_FATAL;
            if ("E".equals(level) || "error".equals(level))
                return LEVEL_ERROR;
            if ("W".equals(level) || "warn".equals(level))
                return LEVEL_WARN;
            if ("I".equals(level) || "info".equals(level))
                return LEVEL_INFO;
            if ("D".equals(level) || "debug".equals(level))
                return LEVEL_DEBUG;
            if ("T".equals(level) || "trace".equals(level))
                return LEVEL_TRACE;
        }
        return LEVEL_INFO;
    }

    protected abstract void log(int level, Object message, Throwable tx);

    protected void log(int level, LogEntity logEntity) {
        log(level, logEntity.message, logEntity.throwable);
    }

    private LogEntity makeInfo(Object log, Object... args) {

        if (log == null)
            return LOGINFO_NULL;

        try {
            LogEntity info = new LogEntity();

            if (log instanceof Throwable) {
                info.throwable = (Throwable) log;
                info.message = info.throwable.getMessage();

            } else if (args == null || args.length == 0) {
                info.message = log.toString();

            } else {
                info.message = String.format(log.toString(), args);
                if (args[args.length - 1] instanceof Throwable) {
                    info.throwable = (Throwable) args[args.length - 1];
                }
            }

            return info;
        } catch (Throwable e) {
            // 格式错误也继续 log
            if (isWarnEnabled())
                warn("String format fail in log , fmt = " + log + " , args = " + Arrays.toString(args), e);
            return LOGINFO_ERROR;
        }
    }


    public void debug(Object message) {
        if (isDebugEnabled())
            log(LEVEL_DEBUG, makeInfo(message));
    }

    public void debugf(String fmt, Object... args) {
        if (isDebugEnabled())
            log(LEVEL_DEBUG, makeInfo(fmt, args));
    }

    public void error(Object message) {
        if (isErrorEnabled())
            log(LEVEL_ERROR, makeInfo(message));
    }

    public void errorf(String fmt, Object... args) {
        if (isErrorEnabled())
            log(LEVEL_ERROR, makeInfo(fmt, args));
    }

    public void fatal(Object message) {
        if (isFatalEnabled())
            log(LEVEL_FATAL, makeInfo(message));
    }

    public void fatalf(String fmt, Object... args) {
        if (isFatalEnabled())
            log(LEVEL_FATAL, makeInfo(fmt, args));
    }

    public void info(Object message) {
        if (isInfoEnabled())
            log(LEVEL_INFO, makeInfo(message));
    }

    public void infof(String fmt, Object... args) {
        if (isInfoEnabled())
            log(LEVEL_INFO, makeInfo(fmt, args));
    }

    public void trace(Object message) {
        if (isTraceEnabled())
            log(LEVEL_TRACE, makeInfo(message));
    }

    public void tracef(String fmt, Object... args) {
        if (isTraceEnabled())
            log(LEVEL_TRACE, makeInfo(fmt, args));
    }

    public void warn(Object message) {
        if (isWarnEnabled())
            log(LEVEL_WARN, makeInfo(message));
    }

    public void warnf(String fmt, Object... args) {
        if (isWarnEnabled())
            log(LEVEL_WARN, makeInfo(fmt, args));
    }

    public boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public boolean isErrorEnabled() {
        return isErrorEnabled;
    }

    public boolean isFatalEnabled() {
        return isFatalEnabled;
    }

    public boolean isInfoEnabled() {
        return isInfoEnabled;
    }

    public boolean isTraceEnabled() {
        return isTraceEnabled;
    }

    public boolean isWarnEnabled() {
        return isWarnEnabled;
    }

    protected String tag = "";

    public Log setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
