package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum EmailSendStatus implements IntegerValuedEnum {
    /** 已受理 */
    ACCEPTED(1),

    /** 成功 */
    SUCCESS(98),

    /** 失败 */
    FAIL(99),
    ;

    private int value;

    private EmailSendStatus(int value) {
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
