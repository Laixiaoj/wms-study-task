package vip.study.parent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vip.study.parent.common.SortEnum;

import java.util.Arrays;

@Service
@Slf4j
public class AsyncServiceDemo {

    private static String EVENT_NAME = "Test";

    @Test
    @Async
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
}
