package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum AuthLevel implements IntegerValuedEnum {
    /** 未认证 */
    NOT_AUTH(0),
    ;

    private int value;

    private AuthLevel(int value) {
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
