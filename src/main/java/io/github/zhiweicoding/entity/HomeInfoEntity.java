package io.github.zhiweicoding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Created by zhiwei on 2022/5/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeInfoEntity {
    private List<OrderSnapEntity> orderSnapEntities;
    private List<SellSnapEntity> sellSnapEntities;
    private List<SellSnapEntity> buySnapEntities;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderSnapEntity {
        private String groupName;
        private int groupNum;
        private String dsl;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SellSnapEntity {
        private String snapName;
        private int snapMoney;
        private String dsl;
    }
}
