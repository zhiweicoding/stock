package io.github.zhiweicoding.tool;

import io.github.zhiweicoding.tool.bean.PageHelperBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartBear
 * @author: zhiwei
 * @email: diaozhiwei2k@163.com
 * @create: 2020-02-07 13:50
 * @description:
 **/
public class PageNumNextTool<T extends PageHelperBean> implements MultiTool<T> {

    @Override
    public T work(T bean) {
        List tAllList = bean.getTAllList();
        int allPageCount = tAllList.size();
        bean.setAllPageCount(allPageCount);
        if (allPageCount == 0) {
            bean.setTList(bean.getTAllList());
            bean.setHasNext(false);
            return bean;
        }
        int everyPageCount = bean.getEveryPageCount();
        if (everyPageCount <= 0) {
            everyPageCount = 1;
        }
        int whichPageNum = bean.getWhichPageNum() - 1;
        if (whichPageNum < 0) {
            whichPageNum = 0;
        }
        int howManyPageCount = allPageCount / everyPageCount;
        int leaveCount = allPageCount % everyPageCount;
        if (leaveCount != 0) {
            leaveCount = 1;
        }
        int startNum = whichPageNum * everyPageCount;
        int startNumOther = howManyPageCount * everyPageCount;
        if (howManyPageCount >= 0) {
            if (whichPageNum > howManyPageCount) {
                if (whichPageNum >= leaveCount + howManyPageCount) {
                    bean.setTList(new ArrayList<>());
                } else {
                    bean.setTList(new ArrayList<>(tAllList.subList(startNumOther, allPageCount)));
                    bean.setWhichPageNum(whichPageNum + 2);
                }
                bean.setHasNext(false);
            } else if (whichPageNum == howManyPageCount) {
                bean.setTList(new ArrayList<>(tAllList.subList(startNum, allPageCount)));
                bean.setWhichPageNum(whichPageNum + 2);
                bean.setHasNext(false);
            } else {
                bean.setTList(new ArrayList<>(tAllList.subList(startNum, startNum + everyPageCount)));
                bean.setWhichPageNum(whichPageNum + 2);
                bean.setHasNext(true);
            }
        }else{
            bean.setTList(new ArrayList<>());
        }
        bean.setTAllList(new ArrayList<>());
        return bean;
    }
}
