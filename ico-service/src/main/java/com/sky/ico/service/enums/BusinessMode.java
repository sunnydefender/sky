package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

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
}
