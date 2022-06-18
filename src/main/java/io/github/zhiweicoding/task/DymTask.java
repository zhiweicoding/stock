package io.github.zhiweicoding.task;

import io.github.zhiweicoding.constants.KeyConstants;
import io.github.zhiweicoding.support.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态定时任务
 *
 * @author by diaozhiwei on 2022/06/02.
 * @email diaozhiwei2k@163.com
 */
@Configuration
public class DymTask implements SchedulingConfigurer {

    @Autowired
    private ApiSupport apiSupport;

    private String infoApiStr = "https://www.cls.cn/nodeapi/refreshTelegraphList?app=CailianpressWeb&lastTime=%s&os=web&sv=7.7.5&sign=0b991ecf85a5c6ab77f2c77bbd7f260b";

    @PostConstruct
    public void init(){
        apiSupport.init(null);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("do job");
                    apiSupport.start(infoApiStr);
                    System.out.println("done");
                },
                triggerContext -> new CronTrigger("0/20 * * * * ?").nextExecutionTime(triggerContext));

        taskRegistrar.addTriggerTask(
                () -> {
                    List<String> stockInfoArray = KeyConstants.stockInfoArray;
                    int size = stockInfoArray.size();
                    if (size > 100) {
                        List<String> removes = new ArrayList<>();
                        for (int i = 0; i < size / 2; i++) {
                            removes.add(stockInfoArray.get(i));
                        }
                        stockInfoArray.removeAll(removes);
                    }
                },
                triggerContext -> new CronTrigger("0 0 0/3 * * ? ").nextExecutionTime(triggerContext));
    }
}
