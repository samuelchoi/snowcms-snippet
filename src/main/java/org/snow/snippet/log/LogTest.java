package org.snow.snippet.log;

import org.snow.snippet.log.impl.Log4jLoggerAdpter;
import org.snow.snippet.log.impl.SystemLoggerAdpter;
import org.snow.snippet.log4j.Log4jExample;

public class LogTest {

    public static void main(String[] args) {
        // log4j
        // LogFactory.setAdapter(new Log4jLoggerAdpter());
        // Log LOGGER = LogFactory.getLogger(Log4jExample.class);

        // system log
        LogFactory.setAdapter(new SystemLoggerAdpter());
        Log log = LogFactory.getLogger(Log4jExample.class);
        log.debug("log debug test");
        log.error("log error test");
    }
}
