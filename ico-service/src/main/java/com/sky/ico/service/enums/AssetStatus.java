package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum AssetStatus implements IntegerValuedEnum {
    /** 正常 */
    USED(1),
    ;

    private int value;

    private AssetStatus(int value) {
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
