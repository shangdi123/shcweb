/* ******************************************************
 * Copyright (C) 2017 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-api.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): liu yanguang <liuyanguang@qiyi.com>
 * 2017/10/25
 * ******************************************************/
package com.hyl.qixiao.domain.qixiao;

import java.util.Date;

public class Advertiser {

    private Long id;
    private String companyName;
    private String businessLicenseName; // 营业执照名称
    private String unifiedSocialCreditCode; // 统一社会信用代码
    private String companyAddress;
    private Long industryId;
    private Long parentIndustryId;
    private Long agentId;
    private Integer orderGroupCount;
    private String contactMail;
    private String contactName;
    private String contactPosition;
    private String contactTelePhone;
    private String contactMobilePhone;
    private Integer bizLineId;
    private String rejectReason;
    private String ICPRegistrationWebsite;
    private Long uid; // 用于发布feed广告的机器人账户
    private String macId; // 用于登录机器人账户生成authcookie
    private String brandName; // 品牌名称，对应passport中昵称
    private String brandLogoUri; // 品牌Logo，对应passport中头像
    private Integer approvalStatus; // 正常，审核中，审核通过，驳回
    private Integer advertiserStatus; // 正常，冻结，无法进入广告主账号
    private Integer crmAdvertiserStatus; // 正常，冻结，可以进入广告主账号，但是不能新建创意; AdvertiserStatus.id
    private Boolean deleteFlag;
    private Date dateCreated;
    private Date lastUpdated;
    private Date qualificationExpireDate;

    public Date getQualificationExpireDate() {
        return qualificationExpireDate;
    }

    public void setQualificationExpireDate(Date qualificationExpireDate) {
        this.qualificationExpireDate = qualificationExpireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessLicenseName() {
        return businessLicenseName;
    }

    public void setBusinessLicenseName(String businessLicenseName) {
        this.businessLicenseName = businessLicenseName;
    }

    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getParentIndustryId() {
        return parentIndustryId;
    }

    public void setParentIndustryId(Long parentIndustryId) {
        this.parentIndustryId = parentIndustryId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getContactName() {
        return contactName;
    }

    public Integer getOrderGroupCount() {
        return orderGroupCount;
    }

    public void setOrderGroupCount(Integer orderGroupCount) {
        this.orderGroupCount = orderGroupCount;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactTelePhone() {
        return contactTelePhone;
    }

    public void setContactTelePhone(String contactTelePhone) {
        this.contactTelePhone = contactTelePhone;
    }

    public String getContactMobilePhone() {
        return contactMobilePhone;
    }

    public void setContactMobilePhone(String contactMobilePhone) {
        this.contactMobilePhone = contactMobilePhone;
    }

    public Integer getBizLineId() {
        return bizLineId;
    }

    public void setBizLineId(Integer bizLineId) {
        this.bizLineId = bizLineId;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getICPRegistrationWebsite() {
        return ICPRegistrationWebsite;
    }

    public void setICPRegistrationWebsite(String ICPRegistrationWebsite) {
        this.ICPRegistrationWebsite = ICPRegistrationWebsite;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogoUri() {
        return brandLogoUri;
    }

    public void setBrandLogoUri(String brandLogoUri) {
        this.brandLogoUri = brandLogoUri;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getAdvertiserStatus() {
        return advertiserStatus;
    }

    public void setAdvertiserStatus(Integer advertiserStatus) {
        this.advertiserStatus = advertiserStatus;
    }

    public Integer getCrmAdvertiserStatus() {
        return crmAdvertiserStatus;
    }

    public void setCrmAdvertiserStatus(Integer crmAdvertiserStatus) {
        this.crmAdvertiserStatus = crmAdvertiserStatus;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
