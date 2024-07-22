package com.hyl.qixiao.feign.skyEye;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/** 企业基本信息
 * @Author maoshunhui@qiyi.com
 * @Date 2019/10/16
 **/
public class CompanyInfo {

    /**
     * 公司id
     */
    private Long id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 统一社会信用代码
     */
    private String creditCode;
    /**
     * 组织机构代码
     */
    private String orgNumber;
    /**
     * 纳税人识别号
     */
    private String taxNumber;
    /**
     * 登记状态
     */
    private String regStatus;

    /**
     * 企业类型
     */
    private String companyOrgType;
    /**
     * 行业
     */
    private String industry;
    /**
     * 成立日期，接口返回的是long 毫秒
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date estiblishTime;
    /**
     * 经营结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date toTime;
    /**
     * 核准时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date approvedTime;
    /**
     * 注册地址
     */
    private String regLocation;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 法人
     */
    private String legalPersonName;
    /**
     * 登记机关
     */
    private String regInstitute;
    /**
     * 天眼查状态
     */
    private Integer skyEyeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getCompanyOrgType() {
        return companyOrgType;
    }

    public void setCompanyOrgType(String companyOrgType) {
        this.companyOrgType = companyOrgType;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getEstiblishTime() {
        return estiblishTime;
    }

    public void setEstiblishTime(Date estiblishTime) {
        this.estiblishTime = estiblishTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getRegInstitute() {
        return regInstitute;
    }

    public void setRegInstitute(String regInstitute) {
        this.regInstitute = regInstitute;
    }

    public Integer getSkyEyeStatus() {
        return skyEyeStatus;
    }

    public void setSkyEyeStatus(Integer skyEyeStatus) {
        this.skyEyeStatus = skyEyeStatus;
    }



}
