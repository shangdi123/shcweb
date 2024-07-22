/* ******************************************************
 * Copyright (C) 2017 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-api.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): Zhong Sheng <nickyzhong@qiyi.com>
 * 2017/12/11
 * ******************************************************/
package com.hyl.qixiao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

public class SqlSessionFactoryUtils {

    public static SqlSessionFactory buildSqlSessionFactory(DataSource dataSource, MybatisProperties properties,
            ResourceLoader resourceLoader) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        if (properties.getConfigLocation() != null) {
            bean.setConfigLocation(resourceLoader.getResource(properties.getConfigLocation()));
        }
        SqlSessionFactory sqlSessionFactory = bean.getObject();

        /* The initial query:
         * 1. to validate the datasource;
         * 2. to accelerate the first query made by users.
         */
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.getConnection().createStatement().executeQuery("SELECT 1");
        }

        return sqlSessionFactory;
    }
}
