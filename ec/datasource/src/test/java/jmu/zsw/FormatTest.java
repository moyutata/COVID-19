package jmu.zsw;

import jmu.zsw.utils.TimeUtil;
import org.junit.jupiter.api.Test;

public class FormatTest {

    @Test
    public void dateTest(){
        String datetime = TimeUtil.format(System.currentTimeMillis(), "yyyyMMdd");
        System.out.println(datetime);
    }
}
