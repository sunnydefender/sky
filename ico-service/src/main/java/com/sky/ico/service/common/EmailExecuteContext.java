package com.sky.ico.service.common;

import com.sky.framework.task.TaskManager;
import com.sky.framework.task.enums.ExecuteStrategy;
import com.sky.framework.task.handler.ITaskHandler;
import com.sky.ico.service.data.entity.PlatformEmail;

/**
 * Created by easyfun on 2018/6/4.
 */
public class EmailExecuteContext {
    public String handler;
    public int sleepMilis = 5000;
    public ExecuteStrategy executeStrategy;
    public ITaskHandler taskHandler;
    public TaskManager taskManager;
    public PlatformEmail platformEmail;

    public static String buildEmailHandler(String className, String emailGroup, String username) {
        return className + ":" + emailGroup + ":" + username;
    }
}
