package org.snow.snippet.log;

public interface LogAdapter {

    /**
     * 获取 logger 对象
     */
    Log getLogger(String className);
}
