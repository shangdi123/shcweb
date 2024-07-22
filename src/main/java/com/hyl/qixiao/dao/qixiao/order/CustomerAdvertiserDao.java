package com.hyl.qixiao.dao.qixiao.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerAdvertiserDao {


    @Select({"select  customer_id from customer_advertiser where advertiser_id = #{advertiserId}"})
    Long selectByCustomerId(@Param("advertiserId") Long advertiserId);

}
