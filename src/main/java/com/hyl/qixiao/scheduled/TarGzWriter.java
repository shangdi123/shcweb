package com.hyl.qixiao.scheduled;

import com.iqiyi.oss.OSSClient;
import com.iqiyi.oss.model.OSSObject;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

public class TarGzWriter {
    public static void writeToTarGz(InputStream inputStream, String outputFilePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);

        // 使用 GZIPOutputStream 包装 FileOutputStream 用来写入压缩数据
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);

        // 设定一个合适的缓冲区大小
        byte[] buffer = new byte[4096];
        int bytesRead;

        // 从 InputStream 读取数据并写入到 GZIPOutputStream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            gzipOutputStream.write(buffer, 0, bytesRead);
        }

        // 完成压缩并关闭所有流
        gzipOutputStream.finish();
        gzipOutputStream.close();
        inputStream.close();
        fileOutputStream.close();
    }




//    public static void writeToTarGzV2(InputStream inputStream, String outputFilePath) throws IOException {
//        OSSClient ossClient = new OSSClient(SERVER, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
//        OSSObject ossObject = ossClient.getObject(BUCKET, objectName);
//        // 读Object内容
//        InputStream inputStream = ossObject.getObjectContent();
//
//        try {
//            IOUtils.copy(inputStream, outputStream);
//        } catch (IOException e) {
//            log.error("error in QOSS download", e);
//        } finally {
//            inputStream.close();
//            ossClient.shutdown();
//        }
//    }
}
