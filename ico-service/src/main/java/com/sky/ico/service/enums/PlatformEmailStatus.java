package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum PlatformEmailStatus implements IntegerValuedEnum {
    /** 未启用 */
    NOT_USED(0),

    /** 已启用 */
    AVAILABLE(1),

    /** 已冻结 */
    FROZEN(2),
    ;

    private int value;

    private PlatformEmailStatus(int value) {
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
