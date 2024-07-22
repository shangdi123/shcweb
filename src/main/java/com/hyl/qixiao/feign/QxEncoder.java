/* ******************************************************
 * Copyright (C) 2019 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-platform-web.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): ding kun <dingkun@qiyi.com>
 * 2019/1/1
 * ******************************************************/
package com.hyl.qixiao.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class QxEncoder implements Encoder {

    private ObjectMapper mapper;

    public QxEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        try {
            JavaType javaType = mapper.getTypeFactory().constructType(bodyType);
            if (javaType.getRawClass() == String.class) {
                template.body(object.toString());
            } else {
                template.body(mapper.writerFor(javaType).writeValueAsString(object));
            }
        } catch (JsonProcessingException e) {
            throw new EncodeException(e.getMessage(), e);
        }
    }
}

