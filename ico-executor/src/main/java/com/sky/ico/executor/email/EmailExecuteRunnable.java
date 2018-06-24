package com.sky.ico.executor.email;

import com.sky.framework.common.utils.JsonUtil;
import com.sky.framework.task.TaskManager;
import com.sky.framework.task.entity.PopTask;
import com.sky.framework.task.entity.TaskPO;
import com.sky.framework.task.entity.builder.TaskPOBuilder;
import com.sky.framework.task.enums.ExecuteStrategy;
import com.sky.framework.task.enums.PopTaskResult;
import com.sky.framework.task.enums.RetryStrategy;
import com.sky.framework.task.enums.TaskStatus;
import com.sky.framework.task.handler.ITaskHandler;
import com.sky.framework.task.handler.TaskExecuteResult;
import com.sky.framework.task.util.DateUtil;
import com.sky.framework.task.util.ThreadUtil;
import com.sky.ico.service.common.EmailExecuteContext;
import com.sky.ico.service.data.entity.PlatformEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by easyfun on 2018/6/4.
 */
public class EmailExecuteRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailExecuteRunnable.class);

    private ITaskHandler taskHandler;
    private TaskManager taskManager;
    private String handlerClassName;
    private String handler;
    private int sleepMilis = 1000;
    private ExecuteStrategy executeStrategy; /* 线程执行模式: 1-等间隔执行一个任务; 其他-等间隔执行到任务获取失败 */
    private PlatformEmail platformEmail;

    public EmailExecuteRunnable(EmailExecuteContext context) {
        if (sleepMilis > 0) {
            this.sleepMilis = context.sleepMilis;
        }
        this.executeStrategy = context.executeStrategy;
        this.taskHandler = context.taskHandler;
        this.taskManager = context.taskManager;
        this.platformEmail = context.platformEmail;
        this.handlerClassName = context.handler;
        this.handler = EmailExecuteContext.buildEmailHandler(this.handlerClassName, platformEmail.getEmailGroup(), platformEmail.getUsername());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                PopTask popTask = taskManager.popExecutingTask(handler, new Date());

                if (PopTaskResult.FAIL_SLEEP == popTask.getPopTaskResult()) {
                    ThreadUtil.safeSleep(sleepMilis);
                    continue;
                }

                if (PopTaskResult.SUCCESS == popTask.getPopTaskResult()) {
                    TaskPO taskPO = popTask.getTaskPO();
                    if (!handler.equals(taskPO.getHandler())) {
                        LOGGER.error("任务handler不存在, handlerName={}", taskPO.getHandler());
                        taskManager.deleteTask(handler, taskPO.getTaskKey());
                        continue;
                    }

                    // 执行任务
                    executeTask(taskPO);

                    if (ExecuteStrategy.NORMAL == executeStrategy) {
                        ThreadUtil.safeSleep(sleepMilis);
                        continue;
                    }
                }
            } catch (Exception e) {
                LOGGER.error("任务执行出错.", e);
            }
        }
    }

    private void executeTask(TaskPO taskPO) {
        Date current = new Date();
        TaskPOBuilder.updateLastTime(taskPO, current);
        if (null == taskPO.getFirstTime()) {
            taskPO.setFirstTime(new Date());
        }
        taskPO.setRetriedTimes(taskPO.getRetriedTimes() + 1);

        TaskPO copyTaskPO = TaskPOBuilder.copy(taskPO);
        TaskExecuteResult result = null;
        try {
            result = taskHandler.execute(copyTaskPO, platformEmail);
        } catch (Exception e) {
            LOGGER.error("执行任务出错,executing queque process error. taskPO={}", JsonUtil.toJSONString(taskPO), e);
            exceptionRetryTask(taskPO);
            return;
        }

        switch (result.getTaskResult()) {
            case SUCCESS:
                successTask(result, taskPO);
                break;
            case FAIL:
                failTask(result, taskPO);
                break;
            case RETRY:
                retryTask(result, taskPO);
                break;
            case MOVE:
                break;
        }
    }

    private void successTask(TaskExecuteResult result, TaskPO taskPO) {
        Date current = new Date();
        TaskPOBuilder.updateDoneTime(taskPO, current);
        taskPO.setTaskStatus(TaskStatus.SUCCESSFUL.getValue());
        taskPO.setProgress(result.getProgress());

        taskManager.successTask(taskPO);
    }

    private void failTask(TaskExecuteResult result, TaskPO taskPO) {
        Date current = new Date();
        TaskPOBuilder.updateDoneTime(taskPO, current);
        taskPO.setTaskStatus(TaskStatus.FAILED.getValue());
        taskPO.setProgress(result.getProgress());

        taskManager.failTask(taskPO);
    }

    private void retryTask(TaskExecuteResult result, TaskPO taskPO) {
        taskPO.setProgress(result.getProgress());
        exceptionRetryTask(taskPO);
    }

    private void exceptionRetryTask(TaskPO taskPO) {
        taskPO.setNextTime(DateUtil.addMiliSeconds(taskPO.getNextTime(), taskPO.getRetryInterval()));

        if (taskPO.getRetryStrategy() == RetryStrategy.NORMAL.getValue() && taskPO.getRetriedTimes() >= taskPO.getMaxRetryTimes()) {
            taskPO.setTaskStatus(TaskStatus.MORE_RETRY_FAILED.getValue());
            taskManager.failTask(taskPO);
        } else {
            taskPO.setTaskStatus(TaskStatus.RETRYING.getValue());
            taskManager.retryTask(taskPO);
        }
    }

    private void moveTask(TaskExecuteResult result, TaskPO taskPO) {
        taskPO.setProgress(result.getProgress());
        taskPO.setNextTime(DateUtil.addMiliSeconds(taskPO.getNextTime(), taskPO.getRetryInterval()));
        taskPO.setTaskStatus(TaskStatus.RETRYING.getValue());
        taskManager.moveTask(taskPO);
    }

}
