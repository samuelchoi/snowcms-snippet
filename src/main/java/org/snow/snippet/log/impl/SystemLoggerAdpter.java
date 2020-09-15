package org.snow.snippet.log.impl;


import org.snow.snippet.log.Log;
import org.snow.snippet.log.LogAdapter;

import java.text.DateFormat;
import java.util.Date;

public class SystemLoggerAdpter implements LogAdapter {

    public Log getLogger(String className) {
        return new SystemLogger();
    }

    static class SystemLogger extends AbstractLogger {

        private SystemLogger(){
            isInfoEnabled = true;
            isDebugEnabled = true;
        }

        private void systemPrintOut(String level, Object message, Throwable t) {
            System.out.printf("[%s][%s][%s] %s\n", getCurrentDate(), level, Thread.currentThread().getName(), message);
            if(t != null){
                t.printStackTrace(System.out);
            }
        }

        private  void systemPrintError(String level, Object message, Throwable t) {
            System.err.printf("[%s][%s][%s] %s\n", getCurrentDate(), level, Thread.currentThread().getName(), message);
            if(t != null){
                t.printStackTrace(System.err);
            }
        }

        private String getCurrentDate() {
            // get current date
            DateFormat df3 = DateFormat.getDateTimeInstance();
            return df3.format(new Date());
        }

        protected void log(int level, Object message, Throwable tx) {
            switch (level) {
                case LEVEL_FATAL:
                    fatal(message, tx);
                    break;
                case LEVEL_ERROR:
                    error(message, tx);
                    break;
                case LEVEL_WARN:
                    warn(message, tx);
                    break;
                case LEVEL_INFO:
                    info(message, tx);
                    break;
                case LEVEL_DEBUG:
                    debug(message, tx);
                    break;
                case LEVEL_TRACE:
                    trace(message, tx);
                    break;
                default:
            }
        }

        public void fatal(Object message, Throwable t) {
            if (isFatalEnabled()) {
                systemPrintError("FATAL", message, t);
            }
        }

        public void error(Object message, Throwable t) {
            if (isErrorEnabled()) {
                systemPrintError("ERROR", message, t);
            }
        }

        public void warn(Object message, Throwable t) {
            if (isWarnEnabled()) {
                systemPrintError("WARN", message, t);
            }
        }

        public void info(Object message, Throwable t) {
            if (isInfoEnabled()) {
                systemPrintOut("INFO", message, t);
            }
        }

        public void debug(Object message, Throwable t) {
            if (isDebugEnabled()) {
                systemPrintOut("DEBUG", message, t);
            }
        }

        public void trace(Object message, Throwable t) {
            if (isTraceEnabled()) {
                systemPrintOut("TRACE", message, t);
            }
        }
    }
}
