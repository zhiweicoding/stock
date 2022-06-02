package io.github.zhiweicoding.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * @Created by zhiwei on 2022/4/2.
 */
public class GeneratorUtils {
    private GeneratorUtils() {
    }

    private static class GeneratorUtilsHolder {
        private static GeneratorUtils generatorUtils = new GeneratorUtils();
    }

    public static GeneratorUtils getInstance() {
        return GeneratorUtilsHolder.generatorUtils;
    }

    public static String getCommonId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static String getQinniuToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "") + getRandomStringByLength(6);
    }

    public static String getRandomNickName() {
        UUID uuid = UUID.randomUUID();
        return "b_" + uuid.toString().replace("-", "").substring(10, 19);
    }

    /**
     * 小程序如果订单编号太长显示有问题
     *
     * @return
     */
    public static String getWxShort() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String currentTimeStr = currentTime.format(formatter);
        String randomStr = getRandomStringByLength(5);
        return currentTimeStr + randomStr;
    }

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
