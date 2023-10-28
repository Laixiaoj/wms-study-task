package vip.study.parent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import vip.study.parent.api.model.TreeNode;
import vip.study.parent.common.SortEnum;
import vip.study.parent.service.CommonService;
import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class) // 解决 @Autowired 注入失败
@SpringBootTest
public class AsyncServiceDemo {

    private static String EVENT_NAME = "Test";

    @Autowired
    CommonService commonService;

    @Test
    public void testAsyncService(){
        try {
            log.info("EVENT_NAME: {}", EVENT_NAME);
            int[] t = new int[3];
            for (int i = 0; i < 3; i++) {
                log.info("begin again data");
                t[i] = i;
            }
            log.info("result: {}", Arrays.toString(t));

        } catch (Exception e) {
            log.error("e: {}",e);
        }
    }

    @Test
    public void case1(){
        SortEnum sortEnum = SortEnum.valueOf("INSERT"); // 获取一个枚举类
        log.info("{}", sortEnum);

    }

    @Test
    public void case2(){
        int[] elements = {3,9,20,0,0,15,7};//0代表空
        TreeNode root = commonService.initBtree(elements,7);
        log.info("{}", root);
    }
}
