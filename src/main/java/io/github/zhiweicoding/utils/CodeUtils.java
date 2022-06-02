package io.github.zhiweicoding.utils;

import lombok.Data;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by zhiwei on 17-7-24.
 */
public class CodeUtils {

    @Data
    public static class CodeEntity {
        private BufferedImage bufferedImage;
        private String code;
    }

    private CodeUtils() {
    }

    private static class CodeUtilsHolder {
        private static CodeUtils codeUtils = new CodeUtils();
    }

    public static CodeUtils getInstance() {
        return CodeUtilsHolder.codeUtils;
    }

    //验证码图片的宽度
    private static int defaultWidth = 108;
    //验证码图片的高度
    private static int defaultHeight = 40;
    //验证码的数量
    private static Random random = new Random();

    public CodeEntity getBufferImage(int width, int height) {
        defaultWidth = width;
        defaultHeight = height;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        Font myFont = new Font("黑体", Font.BOLD, 25);
        g.setFont(myFont);
        g.setColor(getRandomColor(200, 250));
        // 绘制背景
        g.fillRect(0, 0, width, height);
        g.setColor(getRandomColor(180, 200));
        drawRandomLines(g, 160);
        String code = drawRandomString(4, g);
        g.dispose();
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setBufferedImage(image);
        codeEntity.setCode(code);
        return codeEntity;
    }

    public CodeEntity getBufferImage() {
        return getBufferImage(defaultWidth, defaultHeight);
    }

    /**
     * 生成随机颜色
     *
     * @param fc 前景色
     * @param bc 背景色
     * @return Color对象，此Color对象是RGB形式的。
     */
    private static Color getRandomColor(int fc, int bc) {
        if (fc > 255) {
            fc = 200;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    /**
     * 绘制干扰线
     *
     * @param g    Graphics2D对象，用来绘制图像
     * @param nums 干扰线的条数
     */
    private static void drawRandomLines(Graphics2D g, int nums) {
        g.setColor(getRandomColor(160, 200));
        for (int i = 0; i < nums; i++) {
            int x1 = random.nextInt(defaultWidth);
            int y1 = random.nextInt(defaultHeight);
            int x2 = random.nextInt(12);
            int y2 = random.nextInt(12);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 获取随机字符串，
     * 此函数可以产生由大小写字母，汉字，数字组成的字符串
     *
     * @param length 随机字符串的长度
     * @return 随机字符串
     */
    private static String drawRandomString(int length, Graphics2D g) {
        StringBuffer strbuf = new StringBuffer();
        String temp = "";
        int itmp = 0;
        for (int i = 0; i < length; i++) {
            itmp = random.nextInt(10);
//            temp = String.valueOf((char) itmp);
            temp = itmp + "";
            Color color = new Color(20 + random.nextInt(60), 20 + random.nextInt(60), 20 + random.nextInt(60));
            g.setColor(color);
            //想文字旋转一定的角度
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
            //缩放文字
            float scaleSize = random.nextFloat() + 0.9f;
            if (scaleSize > 1f){
                scaleSize = 1f;
            }
            trans.scale(scaleSize, scaleSize);
            g.setTransform(trans);
            g.drawString(temp, 19 * i + 18, 25);
            strbuf.append(temp);
        }
        g.dispose();
        return strbuf.toString();
    }

}
