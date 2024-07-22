/* ******************************************************
 * Copyright (C) 2017 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-api.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): zhuyu <zhuyu@qiyi.com>
 * 2017/11/09
 * ******************************************************/
package com.hyl.qixiao.dao.qixiao.order;

import com.hyl.qixiao.domain.qixiao.Advertiser;
import com.hyl.qixiao.mybatis.SelectInExtendLanguageDriver;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Mapper
@Repository
public interface AdvertiserDao {

    String FIELDS = "id, company_name, business_license_name, unified_social_credit_code, company_address, industry_id, parent_industry_id, agent_id, contact_mail, " +
            "contact_name, contact_position, contact_tele_phone, advertiser_status, approval_status, " +
            "contact_mobile_phone, biz_line_id, reject_reason, icpregistration_website, delete_flag, date_created," +
            "last_updated, crm_advertiser_status, uid, brand_name, brand_logo_uri, mac_id, order_group_count";

    default Advertiser selectById(Long id) {
        List<Advertiser> list = selectByIds(Arrays.asList(id));
        return list.isEmpty() ? null : list.get(0);
    }

    @Lang(SelectInExtendLanguageDriver.class)
    @Select({"select", FIELDS, "from advertiser where id in (#{ids})"})
    List<Advertiser> selectByIds(@Param("ids") List<Long> ids);


}
