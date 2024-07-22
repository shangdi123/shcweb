/* ******************************************************
 * Copyright (C) 2016 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-api
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential
 *
 * @Date: 2018/06/06
 * @Author: zhuyu <zhuyu@qiyi.com>
 * ******************************************************/
package com.hyl.qixiao.serviceImpl;

import com.iqiyi.oss.OSSClient;
import com.iqiyi.oss.model.OSSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportFileQOSS {

    private static final Logger log = LoggerFactory.getLogger(ExportFileQOSS.class);

    @Value("${backendData.accessKey}")
    private String CHARGE_FEES_ACCESS_KEY_ID; // AK
    @Value("${backendData.secretAccessKey}")
    private String CHARGE_FEES_SECRET_ACCESS_KEY; // SK
    @Value("${backendData.bucket}")
    private String CHARGE_FEES_BUCKET;
    @Value("${backendData.server}")
    private String CHARGE_FEES_SERVER;


    public List<String> downloadCreativeCost(String objectName) throws IOException {
        List<String> ret = new ArrayList<>();
        OSSClient ossClient = new OSSClient(CHARGE_FEES_SERVER, CHARGE_FEES_ACCESS_KEY_ID, CHARGE_FEES_SECRET_ACCESS_KEY);
        log.info("bbbbbbbb");
        OSSObject ossObject = ossClient.getObject(CHARGE_FEES_BUCKET, objectName);
        // 读Object内容
        InputStream inputStream = null;
        LineNumberReader reader = null;
        try {
            log.info("ccccccc");
            inputStream = ossObject.getObjectContent();
            reader = new LineNumberReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                ret.add(new String(line.getBytes(), "UTF-8"));
            }
            reader.close();
        } catch (Exception e) {
            log.error("error in QOSS download", e);
        } finally {
            inputStream.close();
            ossClient.shutdown();
            reader.close();
        }
        return ret;
    }
}
