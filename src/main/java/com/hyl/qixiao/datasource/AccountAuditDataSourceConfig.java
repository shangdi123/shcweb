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
package com.hyl.qixiao.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AccountAuditDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.accountAudit")
    public DataSourceProperties accountAuditDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.accountAudit.hikari")
    public DataSource accountAuditDataSource() {
        return accountAuditDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
