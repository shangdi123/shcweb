/* ******************************************************
 * Copyright (C) 2020 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-platform-web.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): Tian Zengguang <tianzengguang@qiyi.com>
 * 2020/05/09
 * ******************************************************/
package com.hyl.qixiao.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OssUploadUtils {

    private static final Map<String, String> CONTENT_TYPE_MAP = new HashMap<>();

    static {
        // 如需指定上传文件类型的contentType 请在这里添加
        CONTENT_TYPE_MAP.put("jpeg", "image/jpeg");
        CONTENT_TYPE_MAP.put("jpg", "image/jpg");
        CONTENT_TYPE_MAP.put("png", "image/png");
        CONTENT_TYPE_MAP.put("html", "text/html");
    }

    public static String generateObjectId(String fileExtension) {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String dir = df.format(now);
        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
        return fileExtension + "/" + dir + "/" + fileName;
    }

    public static String getFileExtension(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static String getContentType(String extension) {
        return CONTENT_TYPE_MAP.getOrDefault(extension, null);
    }
}
