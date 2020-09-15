package org.snow.snippet.log.impl;

import org.snow.snippet.log.Log;
import org.snow.snippet.log.LogAdapter;

public class NopLog implements Log, LogAdapter {
    public Log getLogger(String className) {
        return NOP;
    }

    public static final NopLog NOP = new NopLog();

    protected NopLog() {
    }

    public void warn(Object message, Throwable t) {}

    public void warn(Object message) {}

    public void trace(Object message, Throwable t) {}

    public void trace(Object message) {}

    public boolean isWarnEnabled() {
        return false;
    }

    public boolean isTraceEnabled() {
        return false;
    }

    public boolean isInfoEnabled() {
        return false;
    }

    public boolean isFatalEnabled() {
        return false;
    }

    public boolean isErrorEnabled() {
        return false;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public void info(Object message, Throwable t) {}

    public void info(Object message) {}

    public void fatal(Object message, Throwable t) {}

    public void fatal(Object message) {}

    public void error(Object message, Throwable t) {}

    public void error(Object message) {}

    public void debug(Object message, Throwable t) {}

    public void debug(Object message) {}
}
