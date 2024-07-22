package com.hyl.qixiao.feign.skyEye;
/* ******************************************************
 * Copyright (C) 2019 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-audit-web.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): zhou zhengjie <zhouzhengjie@qiyi.com>
 * 7/30/19
 * ******************************************************/

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyl.qixiao.feign.FeignConfig;
import com.hyl.qixiao.feign.JacksonDecoder;
import com.hyl.qixiao.feign.QxEncoder;
import com.netflix.loadbalancer.ILoadBalancer;
import feign.Client;
import feign.Feign;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.internal.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Configuration
@ConditionalOnClass({ Feign.class, ILoadBalancer.class })
@AutoConfigureBefore({FeignConfig.class})
public class SkyEyeApiConfig {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    private String token = "0e559538-8d14-4731-b256-b0539b075dcf";

    @Autowired
    private Interceptor connectionCloseInterceptor;

    @Autowired
    private Interceptor userAgentInterceptor;

    public static final String EXECUTOR_NAME = "okHttp-dispatcher-api";

    @Bean
    public Feign.Builder qxApiFeignBuilder() {
        return Feign.builder()
                .encoder(qxApiEncoder())
                .decoder(qxApiDecoder())
                .requestInterceptor(contentTypeInterceptor())
                .retryer(Retryer.NEVER_RETRY);
    }

    private Interceptor authInterceptor() {
        return chain -> {
            Request.Builder builder = chain.request().newBuilder()
                    .addHeader("Authorization", token);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        };
    }

    @Bean
    public Encoder qxApiEncoder() {
        return new QxEncoder(MAPPER);
    }

    @Bean
    public Decoder qxApiDecoder() {
        return new ResponseEntityDecoder(new JacksonDecoder(MAPPER));
    }

    @Bean
    public Client qxApiFeignClient(CachingSpringLoadBalancerFactory cachingFactory,
            SpringClientFactory clientFactory) {
        OkHttpClient delegate = new OkHttpClient(qxApiOkHttpClient());
        return new LoadBalancerFeignClient(delegate, cachingFactory, clientFactory);
    }

    @Bean
    public ThreadPoolExecutor qxApiExecutor() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, 10,
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                Util.threadFactory(EXECUTOR_NAME, false));
        return poolExecutor;
    }

    private okhttp3.OkHttpClient qxApiOkHttpClient() {
        Dispatcher dispatcher = new Dispatcher(qxApiExecutor());
        dispatcher.setMaxRequestsPerHost(80);
        dispatcher.setMaxRequests(300);
        return new okhttp3.OkHttpClient().newBuilder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(60, 10L, TimeUnit.MINUTES))
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .addInterceptor(connectionCloseInterceptor)
                .addInterceptor(userAgentInterceptor)
                .addInterceptor(authInterceptor())
                .dispatcher(dispatcher)
                .build();
    }

    @Bean
    public RequestInterceptor contentTypeInterceptor() {
        return template -> template.header("Content-Type", "application/json;charset=UTF-8");
    }

}
