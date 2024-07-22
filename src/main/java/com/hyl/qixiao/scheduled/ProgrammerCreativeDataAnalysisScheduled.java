package com.hyl.qixiao.scheduled;

import com.hyl.qixiao.dao.qixiao.order.AdvertiserDao;
import com.hyl.qixiao.dao.qixiao.order.CustomerAdvertiserDao;
import com.hyl.qixiao.dao.qixiao.order.ProgrammerCreativeDataStatisticsDao;
import com.hyl.qixiao.domain.qixiao.Advertiser;
import com.hyl.qixiao.domain.qixiao.ProgrammerCreativeDataStatisticsDO;
import com.hyl.qixiao.domain.qixiao.QlCreativeVO;
import com.hyl.qixiao.serviceImpl.ExportFileQOSS;
import com.hyl.qixiao.util.JsonUtils;
import com.iqiyi.oss.OSSClient;
import com.iqiyi.oss.model.OSSObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class ProgrammerCreativeDataAnalysisScheduled {

    @Value("${backendData.accessKey}")
    private String CHARGE_FEES_ACCESS_KEY_ID; // AK
    @Value("${backendData.secretAccessKey}")
    private String CHARGE_FEES_SECRET_ACCESS_KEY; // SK
    @Value("${backendData.bucket}")
    private String CHARGE_FEES_BUCKET;
    @Value("${backendData.server}")
    private String CHARGE_FEES_SERVER;

    @Autowired
    private ExportFileQOSS exportFileQOSS;
    @Autowired
    private AdvertiserDao advertiserDao;
    @Autowired
    private CustomerAdvertiserDao customerAdvertiserDao;
    @Autowired
    private ProgrammerCreativeDataStatisticsDao programmerCreativeDataStatisticsDao;

    private static final Logger log = LoggerFactory.getLogger(ProgrammerCreativeDataAnalysisScheduled.class);

   // @Scheduled(cron = "0 0 */24 * * ?")
    public void sayHello() {
        log.info("ProgrammerCreativeDataAnalysisScheduled started");
        List<ProgrammerCreativeDataStatisticsDO> programs = new ArrayList<ProgrammerCreativeDataStatisticsDO>();
        InputStream inputStream = null;
        LineNumberReader reader = null;
        OSSClient ossClient = null;
        try {
            // 1.读取文件名
            Map<Long, ProgrammerCreativeDataStatisticsDO> programsMap = new HashMap<Long, ProgrammerCreativeDataStatisticsDO>();
            String currentFile = "current_file_name";
            String filename = "";
            List<String> filenameList = exportFileQOSS.downloadCreativeCost(currentFile);
            log.info("filenameList:{}", JsonUtils.obj2Str(filenameList));
            if (!org.springframework.util.CollectionUtils.isEmpty(filenameList)) {
                filename = filenameList.get(0);// + ".txt";
            }
            log.info("objectName:{}", filename);
            // 2.下载文件
            ossClient = new OSSClient(CHARGE_FEES_SERVER, CHARGE_FEES_ACCESS_KEY_ID, CHARGE_FEES_SECRET_ACCESS_KEY);
            OSSObject ossObject = ossClient.getObject(CHARGE_FEES_BUCKET, filename);
            inputStream = ossObject.getObjectContent();
            try {
                File outFile = new File("/opt/package/export.tar.gz");
                OutputStream outputStream = new FileOutputStream(outFile);
                IOUtils.copy(inputStream, outputStream);
            } catch (IOException e) {
                log.error("error in QOSS download", e);
            } finally {
                inputStream.close();
                ossClient.shutdown();
            }

            //3.调用shell命令解压export.tar.gz，并且删除export.tar.gz文件
            String cmd = "cd /opt/package && tar -zxvf export.tar.gz";

            log.info(cmd);
            String[] cmdArr = {"/bin/bash", "-c", cmd};
            System.out.println("开始解压");
            Process process = Runtime.getRuntime().exec(cmdArr);
            process.waitFor();

//            Thread.sleep(60 * 1000);
            System.out.println("解压结束");


            //4.读取creative文件流
            File creativeInputFile = new File("/opt/package/creative");
            InputStream creativeInputStream = new FileInputStream(creativeInputFile);
            reader = new LineNumberReader(new InputStreamReader(creativeInputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                log.info("Line: {}" , new String(line.getBytes(), "UTF-8"));
                QlCreativeVO qlCreativeVO = JsonUtils.str2Obj(line, QlCreativeVO.class);
                ProgrammerCreativeDataStatisticsDO data = programsMap.get(qlCreativeVO.getAdvertiserId());
                if (data == null) data = new ProgrammerCreativeDataStatisticsDO();

                List<Long> advertiserIds = new ArrayList<Long>();
                advertiserIds.add(qlCreativeVO.getAdvertiserId());
                List<Advertiser> advertisers = advertiserDao.selectByIds(advertiserIds);
                if (CollectionUtils.isEmpty(advertisers)) {
                    log.error("Advertiser not found, id: {}", qlCreativeVO.getAdvertiserId());
                    continue;
                }

                Advertiser advertiser = advertisers.get(0);
                data.setAdvertiserId(qlCreativeVO.getAdvertiserId());
                if (qlCreativeVO.getGenerationType() == 1) {
                    data.setProgrammerCreativeCount(data.getProgrammerCreativeCount() + 1);
                } else {
                    data.setCommonCreativeCount(data.getCommonCreativeCount() + 1);
                }

                data.setCustomerId(customerAdvertiserDao.selectByCustomerId(qlCreativeVO.getAdvertiserId()));
                data.setDay(new Date());
                data.setParentIndustryId(advertiser.getParentIndustryId());
                programsMap.put(data.getAdvertiserId(), data);
                for (ProgrammerCreativeDataStatisticsDO value : programsMap.values()) {
                    programmerCreativeDataStatisticsDao.insert(value);
                }
            }
            for (ProgrammerCreativeDataStatisticsDO value : programsMap.values()) {
                programmerCreativeDataStatisticsDao.insert(value);
            }
        } catch (Exception e) {
            log.error("error in QOSS download", e);
        } finally {
            try {
                inputStream.close();
                ossClient.shutdown();
                reader.close();
            } catch (Exception e) {
                log.error("error!", e);
            }
        }

        log.info("ProgrammerCreativeDataAnalysisScheduled end");
    }
}
