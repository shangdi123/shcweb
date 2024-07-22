/* ******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-platform-web
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential
 *
 * @Date: 5/31/18
 * @Author(s): ZouNianqing <zounianqing@qiyi.com>
 * ******************************************************/
package com.hyl.qixiao.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Param;

public class JacksonExpander implements Param.Expander {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final JacksonExpander instance = new JacksonExpander();

    private JacksonExpander() {
    }

    public static JacksonExpander getInstance() {
        return instance;
    }

    @Override
    public String expand(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JacksonExpander expand fail", e);
        }
    }
}
