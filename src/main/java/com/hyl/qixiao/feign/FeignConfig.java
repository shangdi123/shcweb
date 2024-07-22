/* ******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-platform-web.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): ding kun <dingkun@qiyi.com>
 * 2018/5/30
 * ******************************************************/
package com.hyl.qixiao.feign;

import com.netflix.loadbalancer.ILoadBalancer;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.cloud.netflix.feign.annotation.PathVariableParameterProcessor;
import org.springframework.cloud.netflix.feign.annotation.RequestHeaderParameterProcessor;
import org.springframework.cloud.netflix.feign.annotation.RequestParamParameterProcessor;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@ConditionalOnClass({ Feign.class, ILoadBalancer.class })
@AutoConfigureBefore({FeignAutoConfiguration.class, FeignClientsConfiguration.class})
public class FeignConfig {


    private String userAgent = "sdf";

    @Bean
    Logger feignLogger() {
        return new QxFeignLogger();
    }

    @Bean
    public Interceptor userAgentInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", userAgent).build();
                return chain.proceed(newRequest);
            }
        };
    }

    @Bean
    public Interceptor connectionCloseInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Connection", "close")
                        .build();
                return chain.proceed(newRequest);
            }
        };
    }

    @Bean
    public Contract contract() {
        return new SpringMvcContract(Arrays.asList(
                new PathVariableParameterProcessor(),
                new RequestHeaderParameterProcessor(),
                new RequestParamParameterProcessor()));
    }

}
