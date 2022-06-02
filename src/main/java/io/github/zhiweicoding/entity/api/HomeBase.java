package io.github.zhiweicoding.entity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author by diaozhiwei on 2022/05/25.
 * @email diaozhiwei2k@163.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeBase {
    //订单相关
    private List<OrderBase> orders;
    private List<SellBase> catSells;
    private List<SellBase> productSells;
    private List<SellBase> adoptSell;

    private int protectNum;
    private int opinionNum;
    private int monthUserNum;
    private int adoptUserNum;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderBase {
        private String name;
        private int number;
        private int order;
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SellBase {
        private String monthStr;
        private double money;
        private int order;
        private String url;
    }


}
