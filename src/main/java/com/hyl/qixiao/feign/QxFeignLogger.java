/* ******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-platform-web.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): ding kun <dingkun@qiyi.com>
 * 2018/5/31
 * ******************************************************/
package com.hyl.qixiao.feign;

import feign.Request;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class QxFeignLogger extends feign.Logger {

    private final Logger logger;

    public QxFeignLogger() {
        this(feign.Logger.class);
    }

    public QxFeignLogger(Class<?> clazz) {
        this(LoggerFactory.getLogger(clazz));
    }

    public QxFeignLogger(String name) {
        this(LoggerFactory.getLogger(name));
    }

    QxFeignLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (logger.isInfoEnabled()) {
            super.logRequest(configKey, logLevel, request);
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        if (logger.isInfoEnabled()) {
            return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(methodTag(configKey) + format, args));
        }
    }
}
