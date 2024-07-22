package com.hyl.qixiao.domain.qixiao;

import java.util.Date;

public class ProgrammerCreativeDataStatisticsDO {

    private Long advertiserId;
    private Long customerId;
    private Long parentIndustryId;
    private Long programmerCreativeCount = 0L;
    private Long commonCreativeCount = 0L;
    private Date day;

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getParentIndustryId() {
        return parentIndustryId;
    }

    public void setParentIndustryId(Long parentIndustryId) {
        this.parentIndustryId = parentIndustryId;
    }

    public Long getProgrammerCreativeCount() {
        return programmerCreativeCount;
    }

    public void setProgrammerCreativeCount(Long programmerCreativeCount) {
        this.programmerCreativeCount = programmerCreativeCount;
    }

    public Long getCommonCreativeCount() {
        return commonCreativeCount;
    }

    public void setCommonCreativeCount(Long commonCreativeCount) {
        this.commonCreativeCount = commonCreativeCount;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
