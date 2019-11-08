package com.zcit.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author hifeng
 * @date 2018/8/2 14:41
 */
public class IdUtil {
    /**
     * 生成UUID
     *
     * @return String
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String genSerialnumber() {
        String letter[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String serialnumber = letter[new Random().nextInt(26)];
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        serialnumber += sdf.format(date);
        for (int i = 0; i < 4; i++) {
            serialnumber += new Random().nextInt(10);
        }
        return serialnumber;
    }
}
