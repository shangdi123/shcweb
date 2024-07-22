package com.hyl.qixiao.dao.qixiao.order;


import com.hyl.qixiao.domain.qixiao.ProgrammerCreativeDataStatisticsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProgrammerCreativeDataStatisticsDao {
    String INSERT_FIELDS = " advertiser_id, customer_id, parent_industry_id, programmer_creative_count, common_creative_count, day";

    @Insert({" INSERT INTO `programmer_creative_data_statistics`( " + INSERT_FIELDS + " ) VALUES (#{data.advertiserId}, #{data.customerId},  #{data.parentIndustryId},#{data.programmerCreativeCount}, #{log.commonCreativeCount}, #{log.day})"})
    int insert(@Param("data") ProgrammerCreativeDataStatisticsDO data);

}
