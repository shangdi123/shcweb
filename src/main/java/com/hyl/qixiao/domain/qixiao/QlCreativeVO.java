/* ******************************************************
 * Copyright (C) 2022 iQIYI.COM - All Rights Reserved
 *
 * This file is part of realtime-dataflow.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): wangguangfu <wangguangfu@qiyi.com>
 * 2022/12/06
 * ******************************************************/
package com.hyl.qixiao.domain.qixiao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QlCreativeVO {

    private Long id;
    private Long advertiserId;
    private Long creativeType; // 创意模板ID
    private Long templateId; // 创意模板ID
    private Integer clickThroughType; // 跳转类型
    private Long landingPageId;
    private String landingPage; // 跳转地址
    private Integer landingPageType;

    private Integer auditStatus; // 审核状态
    private Integer status;


    private Integer materialType; // 素材类型
    private Integer pictureDuration; //pms:ADDEV-21164 针对图片2:1导出固定值5
    private String jsonFormat;
    private List<String> jsonFormatABTest;

    private Long appId;

    private List<Integer> adTag;
    private String adFilmRate;
    private List<Long> adTv;
    private Long albumId;

    private String adContent;
    private Integer adContentParseStatus;
    private String adContentParse;

    private String interactiveAdvertisingDomain; // 跳转地址互动广告域名
    private Boolean interactiveAdvertising; // 是否支持互动推广页

    //DPA相关
    private String dpaProductId;
    private String dpaProductOuterId;

    private Integer submitVersion; //提交版本 addev-10662

    private Integer sourceType;

    // 一键继承
    private Long extendCreativeId;
    private Integer extendCreativeStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date extendCreateTime;

    //addev-19082 视频素材标记：非视频创意0，常规视频=1，5秒图片视频=2，15秒图片视频=3
    private Integer videoMaterialTag;


    // needQRH5
    private Boolean needQRH5;

    private Long version;
    private Integer isValid;//有效1,无效0
    private Integer bizTypeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long exportTime; //当前时间戳

    private String materialContentMd5;
    private Integer materialProduceType; //1:所有图片都是aigc； 0:至少有一个图片不是aigc 或者 为视频

    private Integer generationType; // ADDEV-29448 导出给引擎换了一个名字：0:普通方式生成 1:程序化创意

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public Long getCreativeType() {
        return creativeType;
    }

    public void setCreativeType(Long creativeType) {
        this.creativeType = creativeType;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getClickThroughType() {
        return clickThroughType;
    }

    public void setClickThroughType(Integer clickThroughType) {
        this.clickThroughType = clickThroughType;
    }

    public Long getLandingPageId() {
        return landingPageId;
    }

    public void setLandingPageId(Long landingPageId) {
        this.landingPageId = landingPageId;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public Integer getLandingPageType() {
        return landingPageType;
    }

    public void setLandingPageType(Integer landingPageType) {
        this.landingPageType = landingPageType;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public Integer getPictureDuration() {
        return pictureDuration;
    }

    public void setPictureDuration(Integer pictureDuration) {
        this.pictureDuration = pictureDuration;
    }

    public String getJsonFormat() {
        return jsonFormat;
    }

    public void setJsonFormat(String jsonFormat) {
        this.jsonFormat = jsonFormat;
    }

    public List<String> getJsonFormatABTest() {
        return jsonFormatABTest;
    }

    public void setJsonFormatABTest(List<String> jsonFormatABTest) {
        this.jsonFormatABTest = jsonFormatABTest;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public List<Integer> getAdTag() {
        return adTag;
    }

    public void setAdTag(List<Integer> adTag) {
        this.adTag = adTag;
    }

    public String getAdFilmRate() {
        return adFilmRate;
    }

    public void setAdFilmRate(String adFilmRate) {
        this.adFilmRate = adFilmRate;
    }

    public List<Long> getAdTv() {
        return adTv;
    }

    public void setAdTv(List<Long> adTv) {
        this.adTv = adTv;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public Integer getAdContentParseStatus() {
        return adContentParseStatus;
    }

    public void setAdContentParseStatus(Integer adContentParseStatus) {
        this.adContentParseStatus = adContentParseStatus;
    }

    public String getAdContentParse() {
        return adContentParse;
    }

    public void setAdContentParse(String adContentParse) {
        this.adContentParse = adContentParse;
    }

    public String getInteractiveAdvertisingDomain() {
        return interactiveAdvertisingDomain;
    }

    public void setInteractiveAdvertisingDomain(String interactiveAdvertisingDomain) {
        this.interactiveAdvertisingDomain = interactiveAdvertisingDomain;
    }

    public Boolean getInteractiveAdvertising() {
        return interactiveAdvertising;
    }

    public void setInteractiveAdvertising(Boolean interactiveAdvertising) {
        this.interactiveAdvertising = interactiveAdvertising;
    }

    public String getDpaProductId() {
        return dpaProductId;
    }

    public void setDpaProductId(String dpaProductId) {
        this.dpaProductId = dpaProductId;
    }

    public String getDpaProductOuterId() {
        return dpaProductOuterId;
    }

    public void setDpaProductOuterId(String dpaProductOuterId) {
        this.dpaProductOuterId = dpaProductOuterId;
    }

    public Integer getSubmitVersion() {
        return submitVersion;
    }

    public void setSubmitVersion(Integer submitVersion) {
        this.submitVersion = submitVersion;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Long getExtendCreativeId() {
        return extendCreativeId;
    }

    public void setExtendCreativeId(Long extendCreativeId) {
        this.extendCreativeId = extendCreativeId;
    }

    public Integer getExtendCreativeStatus() {
        return extendCreativeStatus;
    }

    public void setExtendCreativeStatus(Integer extendCreativeStatus) {
        this.extendCreativeStatus = extendCreativeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getExtendCreateTime() {
        return extendCreateTime;
    }

    public void setExtendCreateTime(Date extendCreateTime) {
        this.extendCreateTime = extendCreateTime;
    }

    public Integer getVideoMaterialTag() {
        return videoMaterialTag;
    }

    public void setVideoMaterialTag(Integer videoMaterialTag) {
        this.videoMaterialTag = videoMaterialTag;
    }

    public Boolean getNeedQRH5() {
        return needQRH5;
    }

    public void setNeedQRH5(Boolean needQRH5) {
        this.needQRH5 = needQRH5;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getBizTypeId() {
        return bizTypeId;
    }

    public void setBizTypeId(Integer bizTypeId) {
        this.bizTypeId = bizTypeId;
    }

    public Long getExportTime() {
        return exportTime;
    }

    public void setExportTime(Long exportTime) {
        this.exportTime = exportTime;
    }

    public String getMaterialContentMd5() {
        return materialContentMd5;
    }

    public void setMaterialContentMd5(String materialContentMd5) {
        this.materialContentMd5 = materialContentMd5;
    }

    public Integer getMaterialProduceType() {
        return materialProduceType;
    }

    public void setMaterialProduceType(Integer materialProduceType) {
        this.materialProduceType = materialProduceType;
    }

    public Integer getGenerationType() {
        return generationType;
    }

    public void setGenerationType(Integer generationType) {
        this.generationType = generationType;
    }
}
