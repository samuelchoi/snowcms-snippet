package org.snow.snippet.log.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snow.snippet.log.Log;
import org.snow.snippet.log.LogAdapter;

/**
 * Logger 适配器
 */
public class Log4jLoggerAdpter implements LogAdapter {

    public Log getLogger(String className) {
        return new Log4jLogger(className);
    }

    /**
     * Log4j 适配器实现
     */
    static class Log4jLogger extends AbstractLogger {

        private Logger logger;

        private static boolean hasTrace;

        static {
            try {
                Level.class.getDeclaredField("TRACE");
                hasTrace = true;
            }
            catch (Throwable e) {}
        }

        Log4jLogger(String className) {
            logger = LogManager.getLogger(className);
            isFatalEnabled = logger.isFatalEnabled();
            isErrorEnabled = logger.isErrorEnabled();
            isWarnEnabled = logger.isWarnEnabled();
            isInfoEnabled = logger.isInfoEnabled();
            isDebugEnabled = logger.isDebugEnabled();
            if (hasTrace)
                isTraceEnabled = logger.isTraceEnabled();
        }

        public void debug(Object message, Throwable t) {
            if (isDebugEnabled())
                logger.log(Level.DEBUG, message, t);
        }

        public void error(Object message, Throwable t) {
            if (isErrorEnabled())
                logger.log(Level.ERROR, message, t);

        }

        public void fatal(Object message, Throwable t) {
            if (isFatalEnabled())
                logger.log(Level.FATAL, message, t);
        }

        public void info(Object message, Throwable t) {
            if (isInfoEnabled())
                logger.log(Level.INFO, message, t);
        }

        public void trace(Object message, Throwable t) {
            if (isTraceEnabled())
                logger.log(Level.TRACE, message, t);
            else if ((!hasTrace) && isDebugEnabled())
                logger.log(Level.DEBUG, message, t);
        }

        public void warn(Object message, Throwable t) {
            if (isWarnEnabled())
                logger.log(Level.WARN, message, t);
        }

        @Override
        protected void log(int level, Object message, Throwable tx) {
            switch (level) {
                case LEVEL_FATAL:
                    logger.log(Level.FATAL, message, tx);
                    break;
                case LEVEL_ERROR:
                    logger.log(Level.ERROR, message, tx);
                    break;
                case LEVEL_WARN:
                    logger.log(Level.WARN, message, tx);
                    break;
                case LEVEL_INFO:
                    logger.log(Level.INFO, message, tx);
                    break;
                case LEVEL_DEBUG:
                    logger.log(Level.DEBUG, message, tx);
                    break;
                case LEVEL_TRACE:
                    if (hasTrace)
                        logger.log(Level.TRACE, message, tx);
                    else
                        logger.log(Level.DEBUG, message, tx);
                    break;
                default:
                    break;
            }
        }

        @Override
        public boolean isDebugEnabled() {
            return logger.isDebugEnabled();
        }

        @Override
        public boolean isErrorEnabled() {
            return logger.isErrorEnabled();
        }

        @Override
        public boolean isFatalEnabled() {
            return logger.isFatalEnabled();
        }

        @Override
        public boolean isInfoEnabled() {
            return logger.isInfoEnabled();
        }

        @Override
        public boolean isTraceEnabled() {
            if (!hasTrace)
                return logger.isDebugEnabled();
            return logger.isTraceEnabled();
        }

        @Override
        public boolean isWarnEnabled() {
            return logger.isWarnEnabled();
        }
    }
}
