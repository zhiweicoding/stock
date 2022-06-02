package io.github.zhiweicoding.entity;

import lombok.Data;

import java.util.List;

/**
 * {
 * "_index": "ems",
 * "_type": "_doc",
 * "_id": "HCeAO4AB_M6Kc6K2VktR",
 * "_score": 3.5343425,
 * "_source": {
 * "name": "张无忌",
 * "age": 59,
 * "bir": "2012-12-12",
 * "content": "ElasticSearch是一个基于开源使用Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口",
 * "address": "北京"
 * },
 * "highlight": {
 * "content": [
 * "ElasticSearch是一个基于<span style='color:red'>开</span><span style='color:red'>源</span><span style='color:red'>使</span><span style='color:red'>用</span>Lucene的搜索服务器。",
 * "它提供了一个分布式多<span style='color:red'>用</span>户能力的全文搜索引擎，基于RESTful web接口"
 * ]
 * }
 * }
 *
 * @Created by zhiwei on 2022/4/18.
 */
@Data
public class EsQueryResponse {

    private String _index;
    private String _type;
    private String _id;
    private double _score;
    private SourceBean _source;
    private HighlightBean highlight;


    @Data
    public static class SourceBean {
        private String author;
        private int bid;
        private String content;
        private int fid;
        private String mid;
        private String title;
        private int year;
        private List<String> cs;
        private String bookName;

    }

    @Data
    public static class HighlightBean {
        private List<String> author;
        private List<String> content;
        private List<String> title;
    }
}
