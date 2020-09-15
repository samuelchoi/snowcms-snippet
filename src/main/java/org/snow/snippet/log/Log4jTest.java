package org.snow.snippet.log;

import org.snow.snippet.log.impl.Log4jLoggerAdpter;
import org.snow.snippet.log4j.Log4jExample;

public class Log4jTest {

    public static void main(String[] args) {
        LogFactory.setAdapter(new Log4jLoggerAdpter());
        Log LOGGER = LogFactory.getLogger(Log4jExample.class);
        LOGGER.debug("log debug test");
    }
}
