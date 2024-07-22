/* ******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-script.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): han yunlong <hanyunlong@qiyi.com>
 * 2018/9/4
 * ******************************************************/
package com.hyl.qixiao.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

//@Configuration
public class Mysql8DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.mysql8")
    public DataSourceProperties qixiaoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.mysql8.hikari")
    public DataSource qixiaoDataSource() {
        return qixiaoDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
