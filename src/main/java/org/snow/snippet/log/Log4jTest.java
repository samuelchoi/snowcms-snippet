package org.snow.snippet.log;

import org.apache.log4j.Logger;
import org.snow.snippet.log.impl.Log4jLoggerAdpter;
import org.snow.snippet.log4j.Log4jExample;

public class Log4jTest {
    public static void main(String[] args) {

        LogFactory.setAdapter(new Log4jLoggerAdpter());
        Log log4nut = LogFactory.getLog(Log4jExample.class);
        // assertTrue(log4nut.getClass().getName().contains(Log4jLogAdapter.class.getName()));
        // Logger log4j = LogFactory.getLog(Log4jExample.class);

        // assertEquals(log4nut.isInfoEnabled(), log4j.isInfoEnabled());
        // assertEquals(log4nut.isDebugEnabled(), log4j.isDebugEnabled());
        // assertEquals(log4nut.isTraceEnabled(), log4j.isTraceEnabled());
    }
}
