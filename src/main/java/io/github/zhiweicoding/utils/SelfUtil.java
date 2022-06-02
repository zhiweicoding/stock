package io.github.zhiweicoding.utils;

/**
 * @Created by zhiwei on 2022/3/27.
 */
public class SelfUtil {

    public static boolean isJson(String val) {
        return val.contains("{") && val.contains("}") && val.contains("\"") && val.contains(":");
    }

    public static boolean isEmpty(String any) {
        return any == null || any.equals("") || any.equals("null");
    }

    public static String processTitle(String any, String title) {
        if (any == null) {
            return "";
        }
        if (title == null || title.length() == 0) {
            return any;
        }
        int start = any.indexOf(title);
        int titleLength = title.length();
        return any.substring((start + titleLength));
    }

    public static String throwsAfterTitle(String any, String title) {
        if (any == null) {
            return "";
        }
        if (title == null || title.length() == 0) {
            return any;
        }
        int start = any.indexOf(title);
        return any.substring(0, start);
    }


    public static void main(String[] args) {
        System.out.println("“不忘初心、牢记使命”主题教育总结大会上的重要".indexOf("“"));
        System.out.println(processTitle("“不忘初心、牢记使命”主题“不忘初心、牢记使命”教育总结大会上的重要", "“不忘初心、牢记使命”"));
        System.out.println(throwsAfterTitle("“不忘初心、牢记使命”主题“不忘初心、牢记使命”教育总结大会上的重要", "大会"));
    }
}
