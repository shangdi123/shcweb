/* ******************************************************
 * Copyright (C) 2017 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-api.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): zhou zhengjie <zhouzhengjie@qiyi.com>
 * 2017/10/18
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
@MapperScan(basePackages = "com.hyl.qixiao.dao.audit", annotationClass = Mapper.class,
        sqlSessionTemplateRef = "auditSqlSessionTemplate")
public class AuditMapperConfig {

    @Bean
    public SqlSessionFactory auditSqlSessionFactory(DataSource auditDataSource, MybatisProperties properties,
            ResourceLoader resourceLoader) throws Exception {
        return SqlSessionFactoryUtils.buildSqlSessionFactory(auditDataSource, properties, resourceLoader);
    }

    @Bean
    public SqlSessionTemplate auditSqlSessionTemplate(SqlSessionFactory auditSqlSessionFactory) {
        return new SqlSessionTemplate(auditSqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager auditTransactionManager(DataSource auditDataSource) {
        return new DataSourceTransactionManager(auditDataSource);
    }
}
