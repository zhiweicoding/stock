package io.github.zhiweicoding.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * https://www.cls.cn/detail/1032090
 * @author by diaozhiwei on 2022/06/02.
 * @email diaozhiwei2k@163.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoBean {

    private String title;
    private String content;
    private String brief;
    private int type;
    private int ctime;
    private String level;
    private int id;
    private int reading_num;
    private int comment_num;
    private int share_num;
    private List<String> sub_titles;
    private List<String> images;
    private List<Object> stock_list;

    private List<SubjectsBean> subjects;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubjectsBean {
        private int article_id;
        private int subject_id;
        private String subject_name;
        private String subject_img;
        private String subject_description;
        private int category_id;
        private int attention_num;
        private boolean is_attention;
        private boolean is_reporter_subject;
    }
}
