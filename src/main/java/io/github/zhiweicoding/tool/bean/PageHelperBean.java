package io.github.zhiweicoding.tool.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: smartBear
 * @author: zhiwei
 * @email: diaozhiwei2k@163.com
 * @create: 2020-02-07 14:32
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageHelperBean<T> implements Serializable {

    private int everyPageCount;
    private int whichPageNum;
    private int allPageCount;
    private boolean hasNext;
    private boolean hasBefore;
    private List<T> tList;
    private List<T> tAllList;
    private String anyText;
    private String shopId;
    private String userId;
}
