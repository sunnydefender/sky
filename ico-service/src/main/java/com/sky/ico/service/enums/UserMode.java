package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum UserMode implements IntegerValuedEnum {
    /** 普通用户 */
    NORMAL(1),
    ;

    private int value;

    private UserMode(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
