package com.zhe.springboot;

import com.zhe.springboot.util.DiceUtil;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangzhe
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestA {

    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    @Test
    @PerfTest(threads = 1000, duration = 60000, warmUp = 9000)
    public void test() {
        DiceUtil.dice3();
    }

}
