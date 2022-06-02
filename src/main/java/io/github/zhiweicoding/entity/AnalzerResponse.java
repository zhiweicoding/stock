package io.github.zhiweicoding.entity;

import lombok.Data;

/**
 * @Created by zhiwei on 2022/4/18.
 */
@Data
public class AnalzerResponse {

    private String token;
    private int start_offset;
    private int end_offset;
    private String type;
    private int position;
}
