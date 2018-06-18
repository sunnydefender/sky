package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum RegisterStatus implements IntegerValuedEnum {
    SUCCESS(98),
    FAIL(99),
    ;

    private int value;

    private RegisterStatus(int value) {
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
