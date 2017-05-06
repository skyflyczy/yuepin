/**
 * Created by chubchen on 16/9/3.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yp.trade.util.ApplicationContextUtil;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {


    @Test
    public void SpringStart(){

        System.out.print("spring 已经启动");
        System.out.println(ApplicationContextUtil.getApplicationContext());
    }

}
