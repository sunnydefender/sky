package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;
import com.sky.ico.service.pojo.EmailContent;

public enum BusinessMode implements IntegerValuedEnum {
    /** 注册 */
    REGISTER(1),
    ;

    private int value;

    private BusinessMode(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static EmailContent build(BusinessMode businessMode) {
        EmailContent content = new EmailContent();
        switch (businessMode) {
            case REGISTER:
                content.setSubject("注册验证码");
                content.setContent("您的注册验证码{code}");
                break;
        }
        return content;
    }
}
