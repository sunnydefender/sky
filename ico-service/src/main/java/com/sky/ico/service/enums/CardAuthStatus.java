package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum CardAuthStatus implements IntegerValuedEnum {
    /** 未认证 */
    NOT_AUTH(0),

    /** 已认证 */
    AUTH(1),
    ;

    private int value;

    private CardAuthStatus(int value) {
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
