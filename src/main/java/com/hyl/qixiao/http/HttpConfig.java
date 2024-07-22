/* ******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-script.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): han yunlong <hanyunlong@qiyi.com>
 * 2018/8/9
 * ******************************************************/
package com.hyl.qixiao.http;

import okhttp3.*;
import okhttp3.internal.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class HttpConfig {

    @Value("${spring.userAgent}")
    private String userAgent;

    private static final TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };
    private static final SSLContext trustAllSslContext;
    static {
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
    private static final SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

    private Interceptor connectionCloseInterceptor() {
        return chain -> {
            Request newRequest = chain.request().newBuilder().addHeader("Connection", "close")
                    .build();
            return chain.proceed(newRequest);
        };
    }

    private Interceptor userAgentInterceptor() {
        return chain -> {
            Request newRequest = chain.request().newBuilder().addHeader("user-agent", userAgent).build();
            return chain.proceed(newRequest);
        };
    }

    @Bean(name = "httpExecutor")
    public ExecutorService httpExecutor() {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<>(),
                Util.threadFactory("okHttp-dispatcher-creative-refresh", false));
        return executorService;
    }

    @Bean(name = "httpClient")
    public OkHttpClient creativeRefreshHttpClient() {
        Dispatcher dispatcher = new Dispatcher(httpExecutor());
        dispatcher.setMaxRequestsPerHost(1000);
        dispatcher.setMaxRequests(1000);
        return new OkHttpClient().newBuilder()
            .sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager)trustAllCerts[0])
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(10, 10L, TimeUnit.MINUTES))
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .addInterceptor(connectionCloseInterceptor())
                .addInterceptor(userAgentInterceptor())
                .dispatcher(dispatcher)
                .build();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
