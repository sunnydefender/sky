package com.sky.ico.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static ApplicationContext ctx = null;

    public static void main(String[] args) {
        LOGGER.info("[executor] start");
        if (ctx != null) {
            LOGGER.error("[executor]try to init bootstrap twice");
            throw new IllegalArgumentException("try to init bootstrap twice");
        }
        try {
            ctx = new ClassPathXmlApplicationContext("classpath:application.xml");
            ((ClassPathXmlApplicationContext) ctx).registerShutdownHook();

            LOGGER.info("[executor] init success");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("[executor] init failed", e);
            System.exit(0);
        }
    }
}
