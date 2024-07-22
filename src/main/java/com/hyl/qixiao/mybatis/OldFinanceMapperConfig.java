/* ******************************************************
 * Copyright (C) 2019 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-script.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): han yunlong <hanyunlong@qiyi.com>
 * 2019-10-14
 * ******************************************************/
package com.hyl.qixiao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.hyl.qixiao.dao.oldfinance", annotationClass = Mapper.class,
        sqlSessionTemplateRef = "oldFinanceSqlSessionTemplate")
public class OldFinanceMapperConfig {

    @Bean
    public SqlSessionFactory oldFinanceSqlSessionFactory(DataSource oldFinanceDataSource, MybatisProperties properties,
            ResourceLoader resourceLoader) throws Exception {
        return SqlSessionFactoryUtils.buildSqlSessionFactory(oldFinanceDataSource, properties, resourceLoader);
    }

    @Bean
    public SqlSessionTemplate oldFinanceSqlSessionTemplate(SqlSessionFactory oldFinanceSqlSessionFactory) {
        return new SqlSessionTemplate(oldFinanceSqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager olfFinanceTransactionManager(DataSource oldFinanceDataSource) {
        return new DataSourceTransactionManager(oldFinanceDataSource);
    }
}
