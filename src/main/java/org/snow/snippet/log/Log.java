package org.snow.snippet.log;

public interface Log {

    /* fatal */
    boolean isFatalEnabled();

    void fatal(Object message);

    void fatal(Object message, Throwable t);

    /* error */
    boolean isErrorEnabled();

    void error(Object message);

    void error(Object message, Throwable t);

    /* warn */
    boolean isWarnEnabled();

    void warn(Object message);

    void warn(Object message, Throwable t);

    /* info */
    boolean isInfoEnabled();

    void info(Object message);

    void info(Object message, Throwable t);

    /* debug */
    boolean isDebugEnabled();

    void debug(Object message);

    void debug(Object message, Throwable t);

    /* trace */
    boolean isTraceEnabled();

    void trace(Object message);

    void trace(Object message, Throwable t);
}