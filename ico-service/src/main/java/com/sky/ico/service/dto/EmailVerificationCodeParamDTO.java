package com.sky.ico.service.dto;

import com.sky.framework.common.dto.base.BaseParamDTO;
import com.sky.ico.service.enums.BusinessMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class EmailVerificationCodeParamDTO extends BaseParamDTO {
    @NotBlank
    private String businessId;

    @NotNull
    private BusinessMode businessMode;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public BusinessMode getBusinessMode() {
        return businessMode;
    }

    public void setBusinessMode(BusinessMode businessMode) {
        this.businessMode = businessMode;
    }
}
